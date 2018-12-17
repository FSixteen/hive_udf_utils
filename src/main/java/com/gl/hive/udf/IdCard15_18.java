package com.gl.hive.udf;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentLengthException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.StringObjectInspector;

public class IdCard15_18 extends GenericUDF {

  private static final int[] coefficient = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
  private static final String[] mantissa = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };

  @Override
  public ObjectInspector initialize(ObjectInspector[] arguments) throws UDFArgumentException {
    if (arguments.length != 1) throw new UDFArgumentLengthException("仅支持一个参数: String arg");
    if (!(arguments[0] instanceof StringObjectInspector)) throw new UDFArgumentLengthException("仅支持一个参数: String arg");
    return PrimitiveObjectInspectorFactory.javaStringObjectInspector;
  }

  @Override
  public Object evaluate(DeferredObject[] arguments) throws HiveException {
    StringBuilder result = new StringBuilder(arguments[0].get().toString());
    if (18 == result.length()) return result.toString();
    result.insert(6, "19");
    int sum = 0;
    for (int i = 0; i < result.length(); i++)
      sum += Integer.valueOf(result.substring(i, i + 1)) * coefficient[i];
    String remainder = mantissa[sum % 11];
    result.append(remainder);
    return result.toString();
  }

  @Override
  public String getDisplayString(String[] children) { // 显示函数的帮助信息
    return "身份证号码15转18位!";
  }

}
