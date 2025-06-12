package com.java;

import java.util.concurrent.TimeUnit;

/**
 * @author rensong.pu
 * @date 2025/4/9
 */
public class TstVolatile {

        private  static  boolean  flag  =  false;
        private  static Integer  i  =  0;

        private static Object a = null;
        public  static  void  main(String[]  args)  {        
            new  Thread(()  ->  {
                try  {                
//                    TimeUnit.MILLISECONDS.sleep(100);
                    flag  =  true;                
                    System.out.println("flag 被修改成 true");            
                } 
			catch  (Exception e)  {
                    e.printStackTrace();            
                }        
            }).start();
            while  (!flag)  {
                i++;
            }
            System.out.println("程序结束,i="  +  i);
        }

}
