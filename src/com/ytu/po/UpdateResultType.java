package com.ytu.po;

public enum UpdateResultType {
	CAN_NOT_UPDATE,		// 不能更新了
	NET_ERROR,			// 网路出问题了
	IS_LATEST,			// 是最新版本
	NOT_LATEST,
	NOT_KNOW			// 未知错误
}
