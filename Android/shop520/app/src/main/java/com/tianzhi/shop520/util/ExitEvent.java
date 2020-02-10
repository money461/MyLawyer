package com.tianzhi.shop520.util;

public class ExitEvent {
	public ExitEvent() {
		android.os.Process.killProcess(android.os.Process.myPid());
	}
}
