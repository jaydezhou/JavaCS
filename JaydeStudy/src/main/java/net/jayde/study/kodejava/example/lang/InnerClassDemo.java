package net.jayde.study.kodejava.example.lang;

public class InnerClassDemo {
    private Bean bean;

    /**
     * Inner class, the compiled class will be named InnerClassDemo$Bean.class
     */
    class Bean {
        public int width;
        public int height;

        @Override
        public String toString() {
            return width + " x " + height;
        }
    }

    public InnerClassDemo() {
        Bean bean = new Bean();
        bean.width = 100;
        bean.height = 200;

        this.bean = bean;
    }

    public Bean getBean() {
        return this.bean;
    }

    public static void main(String[] args) {
        InnerClassDemo inner = new InnerClassDemo();
        System.out.println("inner.getBean() = " + inner.getBean());
    }
}
