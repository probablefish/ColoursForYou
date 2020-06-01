package com.example.coloursforyou;

import android.os.Parcel;
import android.os.Parcelable;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MockParcel {

    private int mPosition = 0;
    private List<Object> mStore = new LinkedList<>();
    private Parcel mParcel = mock(Parcel.class);

    static Parcel obtain() {
        return new MockParcel().mParcel;
    }

    private MockParcel() {
        setupWrites();
        setupReads();
        setupOthers();
    }

    private void setupWrites() {
        final Answer<Object> answer = new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock i) {
                final Object arg = i.getArgument(0);
                mStore.add(arg);
                return arg;
            }
        };
        doAnswer(answer).when(mParcel).writeString(anyString());
        doAnswer(answer).when(mParcel).writeParcelable(any(Parcelable.class), anyInt());
    }

    private void setupReads() {
        final Answer<Object> answer = new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock i) {
                return mStore.get(mPosition++);
            }
        };
        when(mParcel.readString()).thenAnswer(answer);
        when(mParcel.readParcelable(any(ClassLoader.class))).then(answer);
    }

    private void setupOthers() {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock i) {
                mPosition = i.getArgument(0);
                return null;
            }
        }).when(mParcel).setDataPosition(anyInt());
    }

}
