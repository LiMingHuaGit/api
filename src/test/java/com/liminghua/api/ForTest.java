package com.liminghua.api;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName forTest
 * @Description: TODO
 * @Author LiMinghua
 * @Date 2020/9/2
 * @Version V1.0
 **/
public class ForTest {
    public static void main(String[] args) {
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();

        for (int i=1;i<=5;i++){
//            if(i<4){
//                a.add(i);
//            }
            a.add(i);
            b.add(i);
        }

        for(int i = 0;i<b.size();i++){
            for (Integer integer : a) {
                if (b.get(i).equals(integer)) {
                    b.remove(i);
                }
            }
        }

        for(Object m:b){
            System.out.println(m);
        }
    }
}
