package com.xhh.demo.http.resolve;

import com.xhh.demo.http.resolve.model.Box;
import com.xhh.demo.http.resolve.model.ChalkBox;
import com.xhh.demo.http.resolve.model.PencilBox;

/**
 * BoxResolve
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-4-24
 * @since 1.6
 */
public class BoxResolve {

    public PencilBox createPencilBox() {
        PencilBox pencilBox = new PencilBox();
        pencilBox.setId("p1");
        pencilBox.setName("p2");
        pencilBox.setColor("p3");
        pencilBox.setSize("p4");
        pencilBox.setPencilBox("pencilBox");
        return pencilBox;
    }

    public ChalkBox createChalkBox() {
        ChalkBox chalkBox = new ChalkBox();
        chalkBox.setId("c1");
        chalkBox.setName("c2");
        chalkBox.setColor("c3");
        chalkBox.setSize("c4");
        chalkBox.setChaklBox("chalkBox");
        return chalkBox;
    }

    public <T extends Box> void resolve(T t) {
        if(t instanceof PencilBox) {
            System.out.println("解析为：PencilBOx");
        }else if(t instanceof  ChalkBox) {
            System.out.println("解析为：ChalkBOx");
        }
        System.out.println(t.toString());
    }

    public static void main(String[] args) {
        BoxResolve boxResolve = new BoxResolve();
        boxResolve.resolve(boxResolve.createChalkBox());
        boxResolve.resolve(boxResolve.createPencilBox());
    }
}
