package com.example.gitinfoapp.test;

import android.os.Handler;
import android.test.ActivityInstrumentationTestCase2;

import com.example.gitinfoapp.ListActivity;
import com.example.gitinfoapp.common.Const;
import com.example.gitinfoapp.task.ListTask;

public class ListActivityTest extends ActivityInstrumentationTestCase2<ListActivity> {

	private ListActivity listActivity;

	private Handler handler;

	public ListActivityTest() {
		super(ListActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		this.listActivity = getActivity();
	}
	
	/**
	 * タイトルのチェックテスト
	 * @throws Exception
	 */
	public void testSetTitle() throws Exception {
		String strTitle = this.listActivity.getString(com.example.gitinfoapp.R.string.app_name);
		assertEquals(listActivity.getTitle(), strTitle);
	}

	/**
	 * 一覧取得処理のテスト
	 * @throws Exception
	 */
	public void testGetList() throws Exception {

		this.handler = new Handler(this.listActivity);
		ListTask listTask = new ListTask(this.handler);

		listTask.run();
		
		do {
			Thread.sleep(1000);
		} while (!listTask.isComp());

		assertEquals(Const.LIST_GET_LIST_TASK_SUCCEED, listTask.getMessage().what);
	}
}
