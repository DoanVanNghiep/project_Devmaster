package vn.com.web.service.Web.Managermaterial.Projecttion;

public class Sql {
    public static final String ORDERDETAIL = "" +
            "select p.NAME name,od.QTY qty,p.PRICE price,p.IMAGE image,p.NOTES notes from orders_details od\n" +
            "inner join `manager-material`.product p on od.IDPRODUCT = p.ID\n" +
            "where od.IDORD = ?";
    public static final String ORDERDETAILUSER = "" +
            "select p.ID id,p.NAME name,od.QTY qty,p.PRICE price,p.IMAGE image,p.NOTES notes\n" +
            "from orders_details od\n" +
            "inner join `manager-material`.product p on od.IDPRODUCT = p.ID\n" +
            "inner join `manager-material`.orders o on od.IDORD = o.ID\n" +
            "where o.IDCUSTOMER = ?";

    public static final String DONHANG1 = "" +
            "select distinct  o.TRANGTHAI trangThai,ot.TOTAL tienShip,o.IDORDERS idOrder, o.ID id, o.TOTAL_MONEY total,o.ORDERS_DATE orderDate\n" +
            "            ,pm.NAME namePayment,tm.NAME nameTransport\n" +
            "            from orders o\n" +
            "            inner join `manager-material`.customer c on o.IDCUSTOMER = c.ID\n" +
            "            inner join `manager-material`.orders_details od on o.ID = od.IDORD\n" +
            "            inner join `manager-material`.orders_payment op on o.ID = op.IDORD\n" +
            "            inner join `manager-material`.payment_method pm on op.IDPAYMENT = pm.ID\n" +
            "            inner join `manager-material`.orders_transport ot on o.ID = ot.IDORD\n" +
            "            inner join `manager-material`.transport_method tm on ot.IDTRANSPORT = tm.ID\n" +
            "            inner join `manager-material`.product p on od.IDPRODUCT = p.ID\n" +
            "            where c.ID = ? and o.ID = ?";
    public static final String PRODUCT_TRANGCHU = "select  p.ID id,p.NAME name,p.IMAGE image,o.TRANGTHAI moTa,p.PRICE price,od.QTY quatity\n" +
            "                from product p\n" +
            "                join orders_details od on p.ID = od.IDPRODUCT\n" +
            "                join orders o on o.ID = od.IDORD\n" +
            "            where o.IDCUSTOMER = ?";

    public static final String DONHANG2 = "" +
            "select o.IDORDERS idOrder,o.TRANGTHAI trangthai,\n" +
            "       o.ORDERS_DATE ordersDate, p.NAME name,od.QTY quantity,p.PRICE price\n" +
            "       from orders o\n" +
            "       join orders_details od on o.ID = od.IDORD\n" +
            "       join product p on p.ID = od.IDPRODUCT\n" +
            "where od.IDPRODUCT = ? and o.IDCUSTOMER = ?";
    public static final String DONHANG3 = "" +
            "select o.IDORDERS idOrder,o.TRANGTHAI trangthai,\n" +
            "       o.ORDERS_DATE ordersDate, p.NAME name,od.QTY quantity,p.PRICE price\n" +
            "       from orders o\n" +
            "       join orders_details od on o.ID = od.IDORD\n" +
            "       join product p on p.ID = od.IDPRODUCT\n" +
            "where od.IDPRODUCT = ? and o.IDCUSTOMER = ?";
    public static final  String XACNHAN_ADMIN = "" +
            "select  p.ID id,p.NAME name,p.IMAGE image,o.TRANGTHAI moTa,p.PRICE price,od.QTY quatity\n" +
            "                            from product p\n" +
            "                            join orders_details od on p.ID = od.IDPRODUCT\n" +
            "                            join orders o on o.ID = od.IDORD\n" +
            "                        where o.TRANGTHAI = 1 or o.TRANGTHAI = 2";
}
