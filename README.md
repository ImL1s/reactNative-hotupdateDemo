# React Native增量更新範例

[hotupdate server(NodeJS)](https://github.com/ImL1s/reactNative-hotupdateNodeServer)

## 參考
- [Android 增量更新 （一） NDK 编译 Bsdiff](https://www.evernote.com/shard/s704/sh/d6075f66-e0d1-4e96-acc5-91c416bc186c/77ab91f13df26ddd4045a9db1b1ecf88)
- [Android JNI 技术的应用](https://www.evernote.com/shard/s704/sh/87a58435-807a-4b25-b74f-d20d9848a0d6/c4a240d9c677280db7505c337be3a3cc)
- [React Native增量升級方案](https://www.evernote.com/l/AsBqZ6a2HQNNpZfstXH80LL476589LE-KYw)
- [Android 增量更新bsdiff和bspatch](https://www.jianshu.com/p/f0f2950dee74)

## 前置作業

- 將AndroidStudio上的端口映射到模擬器or實機上:`adb reverse tcp:5055 tcp:5055`


## 流程

1. 更新ReactNative業務邏輯,將上一版本與本次更新版本做diff拆分,打出差異包(.patch)
2. 將差異包(.patch)放在伺服器上,並且更新版本號,以便客戶端熱更新
3. 客戶端載入本地的js bundle,進入react native入口畫面
3. 客戶端(native)發現有版本更新,下載伺服器上的差異包(.patch)文件
4. 客戶端(native)下載完畢,將差異包(.patch)與common.bundle做合併(patch)
5. 合併完成,詢問用戶是否重新讀取


## 常見問題

#### 為什麼不使用CodePush?

    因為CodePush只有資源做Diff,js code沒有diff.

#### 如何在Android做到diff拆分/patch打包?

    在Android下使用JNI的方式去呼叫C++的程式碼做diff/patch,具體的工具類叫`BsdiffUtils`.
    
    在Client端一般用不到diff的功能,通常是: 程序員產出新版本 -> 做diff -> 更改server update json file -> client熱更新 -> client做patch.
