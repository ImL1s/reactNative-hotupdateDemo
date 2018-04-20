# React Native增量更新範例

[hotupdate server(NodeJS)](https://github.com/ImL1s/reactNative-hotupdateNodeServer)

## 參考
- [Android 增量更新 （一） NDK 编译 Bsdiff](https://www.evernote.com/shard/s704/sh/d6075f66-e0d1-4e96-acc5-91c416bc186c/77ab91f13df26ddd4045a9db1b1ecf88)
- [Android JNI 技术的应用](https://www.evernote.com/shard/s704/sh/87a58435-807a-4b25-b74f-d20d9848a0d6/c4a240d9c677280db7505c337be3a3cc)

## 流程

1. 更新ReactNative業務邏輯,使用common.bundle + businessLogic.bundle打出差異包(.patch)
2. 將差異包(.patch)放在伺服器上,並且更新版本號,以便客戶端熱更新
3. 客戶端發現有版本更新,下載伺服器上的差異包(.patch)文件
4. 客戶端下載完畢,將差異包(.patch)與common.bundle做合併(patch)
