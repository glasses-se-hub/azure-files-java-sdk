# Azure Files Java SDK 動作確認

Azure Java SDKでAzure Filesを触ってみた。  
ファイルアップロードだけ公式通り実装しても上手くいかず少し苦戦した。  
アップロードするファイルのサイズ分のオブジェクトを事前作成する必要があった。  
JUnitは書いてません。

## 使い方
接続文字列にアカウント名とアカウントキーをセットする。

    public static final String connectStr = "DefaultEndpointsProtocol=https;"
      + "AccountName=<Account Name>;" + "AccountKey=<Account Key>";

アカウントキーの作成、確認方法はこちら↓  
[https://docs.microsoft.com/ja-jp/azure/storage/common/storage-account-keys-manage?tabs=azure-portal](https://docs.microsoft.com/ja-jp/azure/storage/common/storage-account-keys-manage?tabs=azure-portal)

## 参考
以下の公式の実装サンプルをほぼそのまま利用してます。  
[Microsoft公式](https://docs.microsoft.com/ja-jp/azure/storage/files/storage-java-how-to-use-file-storage?tabs=java)
