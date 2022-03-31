package com.iyuriy.notification.services;

import javax.annotation.Nullable;

record TimerEventEntry(String timestamp, @Nullable String condition) {
}
