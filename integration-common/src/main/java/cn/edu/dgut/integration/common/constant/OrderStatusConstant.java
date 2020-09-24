package cn.edu.dgut.integration.common.constant;

/**
 * 1-待支付，2-待送达，3-待评论，4-已完成，5-退款中, 6-退款完毕
 */
public class OrderStatusConstant {

    public static final Integer WAIT_TO_PAY = 1;

    public static final Integer WAIT_TO_DELIVERY = 2;

    public static final Integer WAIT_TO_ACCESS = 3;

    public static final Integer DONE = 4;

    public static final Integer WAIT_TO_REFUND = 5;

    public static final Integer REFUNDED = 6;
}
