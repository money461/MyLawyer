package com.tianzhi.shop520.util;

import de.greenrobot.event.EventBus;

/**
 * 事件传递Bus管理器
 *
 * @author markmjw
 * @date 2014-08-16
 */
public class EventBusManager {

    private static final EventBus EVENT_BUS = new EventBus();

    public static void post(Object event) {
        EVENT_BUS.post(event);
    }

    public static void register(Object subscriber) {
        if (!EVENT_BUS.isRegistered(subscriber)) {
            EVENT_BUS.register(subscriber);
        }
    }

    public static void unregister(Object subscriber) {
        if (EVENT_BUS.isRegistered(subscriber)) {
            EVENT_BUS.unregister(subscriber);
        }
    }
}
