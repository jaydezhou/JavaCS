package net.jayde.study.Als4Study;

import net.jayde.study.Algorithms4.Draw;
import net.jayde.study.Algorithms4.StdRandom;

public class testDraw {
    public static void main(String[] args) {
//        Draw draw1 = new Draw("Test client 1");
//        draw1.square(.2, .8, .1);
//        draw1.filledSquare(.8, .8, .2);
//        draw1.circle(.8, .2, .2);
//        draw1.setPenColor(Draw.MAGENTA);
//        draw1.setPenRadius(.02);
//        draw1.arc(.8, .2, .1, 200, 45);
//
//
//        // create another one
//        Draw draw2 = new Draw("Test client 2");
//        draw2.setCanvasSize(900, 200);
//        // draw a blue diamond
//        draw2.setPenRadius();
//        draw2.setPenColor(Draw.BLUE);
//        double[] x = {.1, .2, .3, .2};
//        double[] y = {.2, .3, .2, .1};
//        draw2.filledPolygon(x, y);

//        // text
//        draw2.setPenColor(Draw.BLACK);
//        draw2.text(0.2, 0.5, "bdfdfdfdlack text");
//        draw2.setPenColor(Draw.WHITE);
//        draw2.text(0.8, 0.8, "white text");

        double allx = 500;
        double ally = 500;
        double beginx = 100;
        double beginy = 100;
        double leftx = 100;
        double lefty = 100;
        double[] datas = new double[20];
        for(int i=0;i<datas.length;i++){
            datas[i] = i+1;
        }
        StdRandom.shuffle(datas);
        Draw draw3 = new Draw("");
        draw3.setCanvasSize(500, 500);
        double endx = allx - leftx;
        double endy = ally - lefty;
        double usedweight = allx - beginx - leftx;
        double usedheight = ally - beginy - lefty;
        draw3.setPenRadius();
        draw3.setPenColor(Draw.BLACK);
        draw3.line(beginx / allx, beginy / ally, beginx / allx, endy / ally);
        draw3.line(beginx / allx, beginy / ally, endx / allx, beginy / ally);
        draw3.setPenRadius(0.01);
        draw3.setPenColor(Draw.RED);

        double x0,y0,x1,y1;
        for(int i=0;i<datas.length;i++){
            x0 = x1 = (beginx+(usedweight*(i+1)/datas.length))/allx;
            y0 = beginy/ally;
            y1 = (beginy + usedheight*datas[i]/20)/ally;
            draw3.line(x0,y0,x1,y1);
        }
    }
}
