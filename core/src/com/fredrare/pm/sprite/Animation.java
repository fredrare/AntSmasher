package com.fredrare.pm.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Animation {
    private Array<TextureRegion> mFrames;
    private float mMaxFrameTime;
    private float mCurrentFrameTime;
    private int mFrameCount;
    private int mCurrentFrameIndex;

    public Animation(TextureRegion region, int frameCount, float cycleTime) {
        mFrames = new Array<TextureRegion>();

        int frameWidth = region.getRegionWidth() / frameCount;

        for (int i=0; i< frameCount; i++){
            mFrames.add(new TextureRegion(region, i * frameWidth, 0, frameWidth,
                    region.getRegionHeight()));
        }

        mFrameCount = frameCount;
        mMaxFrameTime = cycleTime / frameCount;
        mCurrentFrameIndex = 0;
    }

    public void update(float dt){
        mCurrentFrameTime += dt;

        if (mCurrentFrameTime > mMaxFrameTime){
            mCurrentFrameIndex++;
            mCurrentFrameTime = 0;
        }
        if (mCurrentFrameIndex == mFrameCount){
            mCurrentFrameIndex = 0;
        }
    }

    public TextureRegion getFrame(){
        return mFrames.get(mCurrentFrameIndex);
    }
}