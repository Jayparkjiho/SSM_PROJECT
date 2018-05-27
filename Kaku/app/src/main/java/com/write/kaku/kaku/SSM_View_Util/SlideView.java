package com.write.kaku.kaku.SSM_View_Util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

public class SlideView extends ViewGroup {

    private static final String JAG = "SlideView";

    // 드래그 속도와 방향을 판단하는 클래스
    private VelocityTracker mVelocityTracker = null;

    // 화면 전환을 위한 드래그 속도의 최소값 pixel/s (100 정도으로 속도로 이동하면 화면전환으로 인식)
    private static final int SNAP_VELOCITY = 1000;

    /* 화면에 대한 터치이벤트가 화면전환을 위한 터치인가? 현 화면의 위젯동작을 위한
        터치인가? 구분하는 값 (누른상태에서 10px 이동하면 화면 이동으로 인식) */
    private int mTouchSlop = 100;

//    private Bitmap mWallpaper = null; // 배경화면을 위한 비트맵
//    private Paint mPaint = null;

    /* 화면 자동 전황을 위한 핵심 클래스 ( 화면 드래그후 손을 뗏을때
        화면 전환이나 원래 화면으로 자동으로 스크롤 되는 동작을 구현하는 클래스) */
    private Scroller mScroller = null;
    private PointF mLastPoint = null; // 마지막 터치 지점을 저장하는 클래스
    private int mCurPage = 0; // 현재 화면 페이지

    private int mCurTouchState; // 현재 터치의 상태
    private static final int TOUCH_STATE_SCROLLING = 0; // 현재 스크롤 중이라는 상태
    private static final int TOUCH_STATE_NORMAL = 1; // 현재 스크롤 상태가 아님

    private Toast mToast;

    public SlideView(Context context) {
        super(context);
        init();
    }

    public SlideView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SlideView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
//        mWallpaper = BitmapFactory.decodeResource(getResources(), R.drawable.blue_test_img); // 배경화면 불러오기
//        mPaint = new Paint();
        mScroller = new Scroller(getContext()); // 스크롤러 클래스 생성
        mLastPoint = new PointF();
    }

    // 차일드뷰의 크기를 지정하는 콜백 메서드
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG, "onMeasure");
        for (int i = 0; i < getChildCount(); i++) {
            // 각 차일드뷰의 크기는 동일하게 설정
            getChildAt(i).measure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    // 차일드뷰의 위치를 지정하는 콜백 메서드
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.d(TAG, "onLayout");
        // 핵심 구현 부분으로써
        // 차일드뷰들을 겹치지 않게 옆으로 차례대로 나열해서 배치한다.
        // 옆으로 차례대로 배치를 해놔야 스크롤을 통해 옆으로 이동하는것이 가능해진다.
        for (int i = 0; i < getChildCount(); i++) {
            int child_left = getChildAt(i).getMeasuredWidth() * i;
            getChildAt(i).layout(child_left, t, child_left + getChildAt(i).getMeasuredWidth(),
                    getChildAt(i).getMeasuredHeight());
        }
    }

    // viewgroup 클래스의 onDraw 메서드라 생각하면 되겠다.
    @Override
    protected void dispatchDraw(Canvas canvas) {
//        canvas.drawBitmap(mWallpaper, 0, 0, mPaint); // 바탕화면을 그리고
        for (int i = 0; i < getChildCount(); i++) {
            drawChild(canvas, getChildAt(i), 100); // 차일드 뷰들을 하나하나 그린다.
        }
    }

    // 수정
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "event Action : " + event.getAction());

        if (mVelocityTracker == null)
            mVelocityTracker = VelocityTracker.obtain();

        mVelocityTracker.addMovement(event);

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                mLastPoint.set(event.getX(), event.getY());
                break;

            case MotionEvent.ACTION_MOVE:
                int x = (int) (event.getX() - mLastPoint.x);
                scrollBy(-x, 0);

                // move 스크롤 이벤트가 가장 처음 일어났을때만
                if (isFirstMove && getChildCount() > 2) {
                    // 현재 전체 페이지보다 상위로 스크롤 했을때, overScroll 이벤트 발생
                    if (x < 0 && mCurPage == getChildCount() - 1) {
                        overScroll();
                    } else if (x > 0 && mCurPage == 0) {
                        // 하위로 스크롤 했을때 underScroll
                        underScroll();
                    }
                }
                isFirstMove = false;

                invalidate();
                mLastPoint.set(event.getX(), event.getY());
                break;

            case MotionEvent.ACTION_UP:
                mVelocityTracker.computeCurrentVelocity(1000);
                int v = (int) mVelocityTracker.getXVelocity();

                int gap = getScrollX() - mCurPage * getWidth();
                Log.d(TAG, "mVelocityTracker : " + v);
                int nextPage = mCurPage;

                if ((v > SNAP_VELOCITY || gap < -getWidth() / 2) && mCurPage > 0) {
                    nextPage--;
                } else if ((v < -SNAP_VELOCITY || gap > getWidth() / 2) && mCurPage < getChildCount() - 1) {
                    nextPage++;
                }

                int move = 0;
                if (mCurPage != nextPage) {
                    move = getChildAt(0).getWidth() * nextPage - getScrollX();
                } else {
                    move = getWidth() * mCurPage - getScrollX();
                }

                mScroller.startScroll(getScrollX(), 0, move, 0, Math.abs(move));

                if (mToast != null) {
                    mToast.setText("page : " + nextPage);
                } else {
                    mToast = Toast.makeText(getContext(), "page : " + nextPage, Toast.LENGTH_SHORT);
                }
                mToast.show();
                invalidate();
                mCurPage = nextPage;

                mCurTouchState = TOUCH_STATE_NORMAL;
                mVelocityTracker.recycle();
                mVelocityTracker = null;
                isFirstMove = true; // move 스크롤 체크용 초기화
                break;
        }

        return true;
    }

    // 완전 핵심!! 인 콜백 메서드. 스크롤 될때마다 무조건 계속 실행됨.
    @Override
    public void computeScroll() {
        super.computeScroll();
        // onTouchEvent 에서 지정된 mScroller 의 목표 스크롤 지점으로 스크롤하는데, 필요한 중간 좌표 값들을
        // 얻기 위한 메서드로서, 중간 좌표값을 얻을수 있으면 true 를 리턴
        if (mScroller.computeScrollOffset()) {
            // 값을 얻을수 있다면. getCurrX,getCurrY 을 통해 전달되는데,
            // 이는 목표 지점으로 스크롤하기 위한 중간 좌표값들을 Scroller 클래스가 자동으로 계산한 값이다.
            // scrollTo() 를 통해 화면을 중간 지점으로 스크롤 하고,
            // 앞서 말했듯 스크롤이 되면 자동으로 computeScroll() 메서드가 호출되기 때문에
            // 목표 스크롤 지점에 도착할떄까지 computeScroll() 메서드가 호출되고 스크롤 되고 호출되고 반복.
            // 따라서 화면에 스크롤 애니메이션을 구현된것처럼 보이게 됨.
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }

    // ViewGroup 의 childview 들에게 터치이벤트를 줄것인지 아니면 본인에게 터치이벤트를 줄것인지
    // 판단하는 콜백 메서드 ( 터치 이빈트 발생시 가장먼저 실행 됨 )
    // 리턴값으로 true 를 주게 되면 viewgroup의 onTouchEvent 메서드가 실행되고
    // false 를 주면 ViewGroup 의 onTouchEvent은 실행되지 않고 childview 에게
    // 터치 이벤틀르 넘겨주게 된다. 따라서, 화전 전환 할것인가? 차일드뷰의 버튼이나 여타 위젯을 컨트롤
    // 하는 동작인가? 를 구분하는 로직이 여기서 필요하다.
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG, "onInterceptTouchEvent : " + ev.getAction());
        int action = ev.getAction();
        int x = (int) ev.getX();
        int y = (int) ev.getY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                // Scroller가 현재 목표지점까지 스크롤 되었지는 판단하는 isFinished() 를 통해
                // 화면이 자동 스크롤 되는 도중에 터치를 한것인지 아닌지를 확인하여,
                // 자식에게 이벤트를 전달해 줄건지를 판단한다.
                mCurTouchState = mScroller.isFinished() ? TOUCH_STATE_NORMAL
                        : TOUCH_STATE_SCROLLING;
                mLastPoint.set(x, y); // 터치 지점 저장
                break;
            case MotionEvent.ACTION_MOVE:
                // 자식뷰의 이벤트인가 아니면 화면전환 동작 이벤트를 판단하는 기준의 기본이 되는
                // 드래그 이동 거리를 체크 계산한다.
                int move_x = Math.abs(x - (int) mLastPoint.x);
                // 만약 처음 터치지점에서 mTouchSlop 만큼 이동되면 화면전환을 위한 동작으로 판단
                if (move_x > mTouchSlop) {
                    mCurTouchState = TOUCH_STATE_SCROLLING; // 현재 상태 스크롤 상태로 전환
                    mLastPoint.set(x, y);
                }
                break;
        }

        // 현재 상태가 스크롤 중이라면 true를 리턴하여 viewgroup의 onTouchEvent 가 실행됨
        return mCurTouchState == TOUCH_STATE_SCROLLING;
    }

    // 추가
    boolean isFirstMove = true; // ACTION_MOVE 의 제일 처음 이벤트만 동작하도록 하는 멤버 변수

    // 추가
    private void underScroll() {
        // 마지막 페이지를 가장 처음페이지 앞으로 이동 시킴
        View v = getChildAt(getChildCount() - 1);
        removeViewAt(getChildCount() - 1);
        addView(v, 0);
        setPage(getChildCount() - 1);
    }

    // 추가
    private void overScroll() {
        // 첫번재 페이지를 가장 마지막 페이지 뒤로 이동시킴
        View v = getChildAt(0);
        removeViewAt(0);
        setPage(0);
        addView(v);
    }

    // 추가
    public void setPage(int p) {
        if (!mScroller.isFinished()) {
            mScroller.abortAnimation();
        }
        if (p < 0 || p > getChildCount())
            return;
        mCurPage = p;
        scrollTo(getWidth() * p, 0);
    }
}
