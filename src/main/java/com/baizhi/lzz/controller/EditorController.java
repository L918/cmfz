package com.baizhi.lzz.controller;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/editor")
public class EditorController {

    //图片上传
    @RequestMapping("/uploadEditor")
    public HashMap<String, Object> uploadEditor(MultipartFile photo, HttpServletRequest request) {

        HashMap<String, Object> map = new HashMap<>();

        try {
            String realPath = request.getSession().getServletContext().getRealPath("/upload/img");
            File file = new File(realPath);
            if (!file.exists()) {

                file.mkdirs();
            }
            String filename = photo.getOriginalFilename();
            String name = new Date().getTime() + "-" + filename;
            photo.transferTo(new File(realPath, name));

            //获取http
            String scheme = request.getScheme();

            //获取localhost
            String serverName = request.getServerName();

            //获取 端口号
            int serverPort = request.getServerPort();

            //获取项目名
            String contextPath = request.getContextPath();

            //拼接网络路径
            String url = scheme + "://" + serverName + ":" + serverPort + contextPath + "/upload/img/" + name;

            map.put("error", 0);
            map.put("url", url);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error", 1);
            map.put("message", "上传失败");
        }

        return map;
    }



         /*
        *
        *
    {
      "moveup_dir_path": "",
      "current_dir_path": "",
      "current_url": "/ke4/php/../attached/",
      "total_count": 5,
      "file_list": [
        {
          "is_dir": false,
          "has_file": false,
          "filesize": 208736,
          "dir_path": "",
          "is_photo": true,
          "filetype": "jpg",
          "filename": "1241601537255682809.jpg",
          "datetime": "2018-06-06 00:36:39"
        }
      ]
    }
    * */

    //查询图片空间的图片
    @RequestMapping("/queryPhotos")
    public HashMap<String, Object> queryPhotos(HttpServletRequest request) {

        HashMap<String, Object> map = new HashMap<>();

        ArrayList<Object> list = new ArrayList<>();

        String realPath = request.getSession().getServletContext().getRealPath("/upload/editor");
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String[] names = file.list();
        for (int i = 0; i < names.length; i++) {

            //文件名
            String name = names[i];
            /*
            *
                {
                  "is_dir": false,
                  "has_file": false,
                  "filesize": 14966,
                  "dir_path": "",
                  "is_photo": true,
                  "filetype": "jpg",
                  "filename": "1_192040_1.jpg",
                  "datetime": "2018-06-06 00:36:39"
                }
            * */
            HashMap<String, Object> maps = new HashMap<>();
            maps.put("is_dir", false);              //是否是文件夹
            maps.put("has_file", false);            //是否有文件
            File f = new File(realPath, name);
            maps.put("filesize", f.length());       //文件的大小
            maps.put("is_photo", true);             //是否是图片
            String extension = FilenameUtils.getExtension(name);   //获取图片的类型
            maps.put("filetype", extension);        //图片的类型
            maps.put("filename", name);             //图片的名字
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String datetime = sdf.format(new Date());
            maps.put("datetime", datetime);         //图片的上传时间

            list.add(maps);
        }

         /*
        *   "moveup_dir_path": "",
              "current_dir_path": "",
              "current_url": "/ke4/php/../attached/",
              "total_count": 5,
              "file_list":
        * */
        map.put("current_url", "http://localhost:8989/cmfz/upload/img/");
        map.put("total_count", list.size());
        map.put("file_list",list);

        return map;
    }
}
