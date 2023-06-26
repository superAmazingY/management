package supreme.util;

public class StrUtil {

	//判断是否非空
	public static boolean isEmpty(String str)
	{
		if("".equals(str)||str==null)
		{
			return true;
		}
		return false;
	}

}
