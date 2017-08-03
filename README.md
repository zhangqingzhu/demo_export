# demo_export

dmeo包含了两种导出，Excel和Word，Excel方法比较详细，Word的话 有多种导出方式，例如拼html之类的，但是都不如FreeMarker好用一些，故，word的demo 只采用
了一种方式(两种导出：本地和网页版)作为说明，并且没有图片这个小功能，实际项目中遇到过，但是没有添加进去，比较遗憾。

说明：String imagesBase64 = DocumentHandler.getImageStr(user.getImgurl()));     user.setImage(imagesBase64);

  DocumentHandler工具类中对于图片作了处理，同样可以实现。
  
  其中，因为使用了maven，所以在导出网页版的时候，并没有导出到指定路径，但是实际中，工具类中的那个DocumentHandler.createDoc()方法是既可以作为本地使用，
  也可以作为网页使用的，但是没有发布在tomcat下，所以受到限制。
  
 详情参考实际中的项目。
 String exportFilePath = getExportPath("checked-report.doc");
 String realPath = getRealyPath(exportFilePath);
 
 获取到的是服务器下的地址
