package com.alex.proxy;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @Title: Proxy @ProjectName proxy-pattern @Description: TODO @Author jiangwei121 @Date
 * 2019/4/2418:47
 */
public class Proxy {

  public static Object newProxyInstance(Class inf, InvocationHandler handler)
      throws IOException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException,
          InvocationTargetException, InstantiationException {
    TypeSpec.Builder typeSpecBuilder =
        TypeSpec.classBuilder("TimeProxy").addModifiers(Modifier.PUBLIC).addSuperinterface(inf);

    FieldSpec fieldSpec = FieldSpec.builder(InvocationHandler.class, "handler", Modifier.PRIVATE).build();
    typeSpecBuilder.addField(fieldSpec);

    MethodSpec constructorMethodSpec =
        MethodSpec.constructorBuilder()
            .addModifiers(Modifier.PUBLIC)
            .addParameter(InvocationHandler.class, "handler")
            .addStatement("this.handler  = handler ")
            .build();

    typeSpecBuilder.addMethod(constructorMethodSpec);

    Method[] methods = inf.getDeclaredMethods();

    for (Method method : methods) {
      MethodSpec methodSpec =
          MethodSpec.methodBuilder(method.getName())
              .addModifiers(Modifier.PUBLIC)
              .addAnnotation(Override.class)
              .returns(method.getReturnType())
              .addCode("try {\n")
              .addStatement(
                  "\t$T method = "
                      + inf.getName()
                      + ".class.getMethod(\""
                      + method.getName()
                      + "\")",
                  Method.class)
              //仅作为演示，参数直接硬编码
              .addStatement("\tthis.handler.invoke(this, method, null)")
              .addCode("} catch(Exception e) {\n")
              .addCode("\te.printStackTrace();\n")
              .addCode("}\n")
              .build();
      typeSpecBuilder.addMethod(methodSpec);
    }

    JavaFile javaFile = JavaFile.builder("com.alex.proxy", typeSpecBuilder.build()).build();

    String sourcePath = "C:\\Users\\jiangwei121\\Desktop\\";

    javaFile.writeTo(new File(sourcePath));

    JavaCompiler.compile(new File(sourcePath + "\\com\\alex\\proxy\\TimeProxy.java"));

    File file = new File("C:\\Users\\jiangwei121\\Desktop\\");

    URL url = file.toURI().normalize().toURL();

    URL[] urls = new URL[] {url};

    URLClassLoader classLoader =
        new URLClassLoader(urls, Thread.currentThread().getContextClassLoader());

    Class clazz = classLoader.loadClass("com.alex.proxy.TimeProxy");

    Constructor constructor = clazz.getConstructor(InvocationHandler.class);

    Object obj = constructor.newInstance(handler);

    return obj;
  }

  public static void main(String[] args) {
    try {
      Proxy.newProxyInstance(Flyable.class, new MyInvocationHandler(new Bird()));
    } catch (IOException
        | NoSuchMethodException
        | ClassNotFoundException
        | IllegalAccessException
        | InvocationTargetException
        | InstantiationException e) {
      e.printStackTrace();
    }
  }
}
