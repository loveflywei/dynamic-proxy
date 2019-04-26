package com.alex.proxy;

import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;


/**
 * @Title: JavaCompiler
 * @ProjectName proxy-pattern
 * @Description: TODO
 * @Author jiangwei121
 * @Date 2019/4/2511:14
 */
public class JavaCompiler {
    public static void compile(File javaFile) throws IOException {
        javax.tools.JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = javaCompiler.getStandardFileManager(null,null,null);
        Iterable<? extends JavaFileObject> javaFileObjects = fileManager.getJavaFileObjects(javaFile);
        javax.tools.JavaCompiler.CompilationTask task = javaCompiler.getTask(null, fileManager, null, null, null, javaFileObjects);
        task.call();
        fileManager.close();
    }


}
