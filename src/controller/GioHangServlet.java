/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.ChiTietDonHang;
import entities.Item;
import entities.KhachHang;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ChiTietDonHangModel;
import model.DonHangModel;
import model.GioHangModel;
import model.KhachHangModel;

/**
 *
 * @author home
 */
@WebServlet(name = "GioHangServlet", urlPatterns = {"/GioHangServlet"})
public class GioHangServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // tao 1 doi tuong
    GioHangModel model = new GioHangModel();
    KhachHangModel khachhang_model=new KhachHangModel();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
 
            // khai bao
            String page = "";
            String message = "";
            int masp = 0;
            if (request.getParameter("txtmasp") != null) {
                masp = Integer.parseInt(request.getParameter("txtmasp"));
            }
            String yeucau = request.getParameter("yeucau");

            if (yeucau.equals("muasp")) {
                // them vao gio hang
                model.addSanPham(masp);
                // lay cac Items co trong gio hang ra
                // de qua trang giohang.jsp chung ta hien thá»‹ ra
                request.setAttribute("giohang", model.getListItems());
            }
            if (yeucau.equals("bosp")) {
                //gá»�i hÃ m xÃ³a mÃ³n hÃ ng
                model.removeSanPham(masp);
                //hiá»ƒn thá»‹ cÃ¡c mÃ³n hÃ ng cÃ²n láº¡i trong giá»� hÃ ng
                request.setAttribute("giohang", model.getListItems());
            }
            if (yeucau.equals("xoatatca")) {
                //gá»�i hÃ m xÃ³a háº¿t giá»� hÃ ng
                model.removeAllSanPham();
//                //hiá»ƒn thá»‹  giá»� hÃ ng
//                request.setAttribute("giohang", model.getListItems());
            }
            if (yeucau.equals("tang")) {
                model.tang(masp);
                request.setAttribute("giohang", model.getListItems());
            }
            if (yeucau.equals("giam")) {
                model.giam(masp);
                request.setAttribute("giohang", model.getListItems());
            }
            if (yeucau.equals("thanhtoan")) {
                //1. thÃªm insert into khÃ¡ch hÃ ng
                //2. láº¥y getMax_MaKH vá»«a thÃªm
                //3. cÃ¡c cÃ¢u lá»‡nh phÃ­a dÆ°á»›i hÃ´m trÆ°á»›c Ä‘Ã£ lÃ m
                String TenKH=request.getParameter("form-control");
                String DiaChi=request.getParameter("ct-address");
                String DienThoai=request.getParameter("ct-phone-num");
                KhachHang khachhang=new KhachHang(0, TenKH, DiaChi, DienThoai);
                khachhang_model.insertKhachHang(khachhang);
                System.out.println("khach hang sg");
                int Max_MaKH=khachhang_model.getMaKH_MoiNhat();
                
                //láº¥y ngÃ y hiá»‡n táº¡i (ngÃ y há»‡ thá»‘ng)
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Calendar cal = Calendar.getInstance();
                String ngayDH = dateFormat.format(cal.getTime());
                //1. insert don hang moi
                DonHangModel donhang_model = new DonHangModel();
                //lÆ°u xuá»‘ng Ä‘Æ¡n hÃ ng ngayDH vÃ  á»Ÿ Ä‘Ã¢y táº¡i maKH = 1
                donhang_model.insertDonHang(ngayDH, Max_MaKH); // khach hang cÃ³ mÃ£ lÃ  1
                //2. láº¥y maDH vá»«a insert trong DonHang(tá»©c lÃ  maDH lá»›n nháº¥t)
                int maDH_MoiNhat = donhang_model.getMaDH_MoiNhat();
                //3. insert chi tiáº¿t giá»� hÃ ng vÃ o báº£ng chi tiet don hÃ ng
                //dÃ¹ng vÃ²ng láº·p duyá»‡t qua giá»� hÃ ng vÃ  thÃªm vÃ o báº£ng CTDH
                ChiTietDonHangModel ctdh_model = new ChiTietDonHangModel();
                ArrayList<Item> giohang = model.getListItems();// láº¥y tá»«ng item trong giá»� hÃ ng ra
                int kq = 0;
                for (Item i : giohang) {
                    ChiTietDonHang ctdh = new ChiTietDonHang(maDH_MoiNhat, i.getSanpham().getMaSP(), i.getSanpham().getDonGia(), i.getSoluong());
                    kq = ctdh_model.insertChiTietDonHang(ctdh);
                }
                //4. xÃ³a giá»� hÃ ng
                model.removeAllSanPham();//sau khi thÃªm xuá»‘ng database thÃ¬ ta xÃ³a giá»� hÃ ng
                page = "thongbao.jsp";// chuyá»ƒn qua trang thongbao.jsp xuáº¥t ra maDH vá»«a thÃªm
                message = "Order number " + maDH_MoiNhat + " has been paid successfully";
                request.setAttribute("thongbao", message);
                request.getRequestDispatcher(page).forward(request, response);
                return;
            }
            // chuyá»ƒn trang
            page = "giohang.jsp";
            // 2. Láº¥y giá»� hÃ ng Ä‘á»ƒ trang giohang.jsp xuáº¥t ra cÃ¡c item Ä‘Ã£ mua
            request.setAttribute("giohang", model.getListItems());
            request.setAttribute("tongtien", model.getTongTien());
            request.getRequestDispatcher(page).forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
