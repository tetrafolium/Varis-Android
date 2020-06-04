package com.khmelenko.lab.varis.presenter;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import com.khmelenko.lab.varis.mvp.MvpPresenter;
import com.khmelenko.lab.varis.mvp.MvpView;
import com.khmelenko.lab.varis.repositories.search.SearchResultsView;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for base {@link MvpPresenter}
 *
 * @author Dmytro Khmelenko (d.khmelenko@gmail.com)
 */
public class TestMvpPresenter {

private MvpPresenter<MvpView> mPresenter;
private MvpView mView;

@Before
public void setup() {
	mPresenter = spy(MvpPresenter.class);
	mView = mock(SearchResultsView.class);
}

@Test
public void testAttachDetach() {
	mPresenter.attach(mView);
	verify(mPresenter).onAttach();
	assertNotNull(mPresenter.getView());

	mPresenter.detach();
	verify(mPresenter).onDetach();
	assertNull(mPresenter.getView());
}
}
