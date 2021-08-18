package com.glasses_se_note.azure_files_java_sdk;

import com.azure.storage.file.share.ShareClient;
import com.azure.storage.file.share.ShareClientBuilder;
import com.azure.storage.file.share.ShareDirectoryClient;
import com.azure.storage.file.share.ShareFileClient;
import com.azure.storage.file.share.ShareFileClientBuilder;
import java.io.File;

/**
 * Azure Files Java SDK 動作確認.
 *
 */
public class AzureFilesSamples {

  /**
   * 接続文字列. Account Name、Account Keyは環境に応じて設定してください.
   */
  public static final String connectStr = "DefaultEndpointsProtocol=https;"
      + "AccountName=<Account Name>;" + "AccountKey=<Account Key>";

  /**
   * メイン関数.
   *
   * @param args 引数
   */
  public static void main(String[] args) {
 
    // 呼び出し例
    System.out.println("ファイル共有の作成開始");
    Boolean result = false;
    result = createFileShare(connectStr, "test20210810");
    System.out.println("ファイル共有の作成終了 結果:" + result);

  }

  /**
   * ファイル共有の作成.
   *
   * @param connectStr 接続文字列
   * @param shareName ファイル共有名
   * @return Boolean 実行結果
   */
  public static Boolean createFileShare(String connectStr, String shareName) {
    try {
      ShareClient shareClient =
          new ShareClientBuilder().connectionString(connectStr).shareName(shareName).buildClient();

      shareClient.create();
      return true;
    } catch (Exception e) {
      System.out.println("createFileShare exception: " + e.getMessage());
      return false;
    }
  }

  /**
   * ファイル共有の削除.
   *
   * @param connectStr 接続文字列
   * @param shareName ファイル共有名
   * @return Boolean 実行結果
   */
  public static Boolean deleteFileShare(String connectStr, String shareName) {
    try {
      ShareClient shareClient =
          new ShareClientBuilder().connectionString(connectStr).shareName(shareName).buildClient();

      shareClient.delete();
      return true;
    } catch (Exception e) {
      System.out.println("deleteFileShare exception: " + e.getMessage());
      return false;
    }
  }

  /**
   * ファイルとディレクトリの一覧を表示.
   *
   * @param connectStr 接続文字列
   * @param shareName ファイル共有名
   * @param dirName ディレクトリ名
   * @return Boolean 実行結果
   */
  public static Boolean enumerateFilesAndDirs(String connectStr, String shareName, String dirName) {
    try {
      ShareDirectoryClient dirClient = new ShareFileClientBuilder().connectionString(connectStr)
          .shareName(shareName).resourcePath(dirName).buildDirectoryClient();

      dirClient.listFilesAndDirectories().forEach(fileRef -> System.out
          .printf("Resource: %s\t Directory? %b\n", fileRef.getName(), fileRef.isDirectory()));

      return true;
    } catch (Exception e) {
      System.out.println("enumerateFilesAndDirs exception: " + e.getMessage());
      return false;
    }
  }

  /**
   * ディレクトリ作成.
   *
   * @param connectStr 接続文字列
   * @param shareName ファイル共有名
   * @param dirName ディレクトリ名
   * @return Boolean 実行結果
   */
  public static Boolean createDirectory(String connectStr, String shareName, String dirName) {
    try {
      ShareDirectoryClient dirClient = new ShareFileClientBuilder().connectionString(connectStr)
          .shareName(shareName).resourcePath(dirName).buildDirectoryClient();

      dirClient.create();
      return true;
    } catch (Exception e) {
      System.out.println("createDirectory exception: " + e.getMessage());
      return false;
    }
  }

  /**
   * ディレクトリ削除.
   *
   * @param connectStr 接続文字列
   * @param shareName ファイル共有名
   * @param dirName ディレクトリ名
   * @return Boolean 実行結果
   */
  public static Boolean deleteDirectory(String connectStr, String shareName, String dirName) {
    try {
      ShareDirectoryClient dirClient = new ShareFileClientBuilder().connectionString(connectStr)
          .shareName(shareName).resourcePath(dirName).buildDirectoryClient();

      dirClient.delete();
      return true;
    } catch (Exception e) {
      System.out.println("deleteDirectory exception: " + e.getMessage());
      return false;
    }
  }

  /**
   * ファイルアップロード.
   *
   * @param connectStr 接続文字列
   * @param shareName ファイル共有名
   * @param dirName ディレクトリ名
   * @param fileName ファイル名
   * @param localFilePath アップロードファイルパス
   * @return Boolean 実行結果
   */
  public static Boolean uploadFile(String connectStr, String shareName, String dirName,
      String fileName, String localFilePath) {
    try {
      ShareDirectoryClient dirClient = new ShareFileClientBuilder().connectionString(connectStr)
          .shareName(shareName).resourcePath(dirName).buildDirectoryClient();

      ShareFileClient fileClient = dirClient.getFileClient(fileName);

      File file = new File(localFilePath);

      fileClient.create(file.length());
      fileClient.uploadFromFile(localFilePath);

      return true;
    } catch (Exception e) {
      System.out.println("uploadFile exception: " + e.getMessage());
      return false;
    }
  }

  /**
   * ファイルダウンロード.
   *
   * @param connectStr 接続文字列
   * @param shareName ファイル共有名
   * @param dirName ディレクトリ名
   * @param destDir 出力先ディレクトリ
   * @param fileName ファイル名
   * @return Boolean 実行結果
   */
  public static Boolean downloadFile(String connectStr, String shareName, String dirName,
      String destDir, String fileName) {
    try {
      ShareDirectoryClient dirClient = new ShareFileClientBuilder().connectionString(connectStr)
          .shareName(shareName).resourcePath(dirName).buildDirectoryClient();

      ShareFileClient fileClient = dirClient.getFileClient(fileName);

      // Create a unique file name
      String date = new java.text.SimpleDateFormat("yyyyMMdd-HHmmss").format(new java.util.Date());
      String destPath = destDir + "/" + date + "_" + fileName;

      fileClient.downloadToFile(destPath);
      return true;
    } catch (Exception e) {
      System.out.println("downloadFile exception: " + e.getMessage());
      return false;
    }
  }

  /**
   * ファイル削除.
   *
   * @param connectStr 接続文字列
   * @param shareName ファイル共有名
   * @param dirName ディレクトリ名
   * @param fileName ファイル名
   * @return Boolean 実行結果
   */
  public static Boolean deleteFile(String connectStr, String shareName, String dirName,
      String fileName) {
    try {
      ShareDirectoryClient dirClient = new ShareFileClientBuilder().connectionString(connectStr)
          .shareName(shareName).resourcePath(dirName).buildDirectoryClient();

      ShareFileClient fileClient = dirClient.getFileClient(fileName);
      fileClient.delete();
      return true;
    } catch (Exception e) {
      System.out.println("deleteFile exception: " + e.getMessage());
      return false;
    }
  }
}
