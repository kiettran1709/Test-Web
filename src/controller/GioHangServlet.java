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
                // de qua trang giohang.jsp chung ta hien th??????? ra
                request.setAttribute("giohang", model.getListItems());
            }
            if (yeucau.equals("bosp")) {
                //g???????i h????m x????a m????n h????ng
                model.removeSanPham(masp);
                //hi??????n th??????? c????c m????n h????ng c????n l??????i trong gi??????? h????ng
                request.setAttribute("giohang", model.getListItems());
            }
            if (yeucau.equals("xoatatca")) {
                //g???????i h????m x????a h??????t gi??????? h????ng
                model.removeAllSanPham();
//                //hi??????n th???????  gi??????? h????ng
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
                //1. th????m insert into kh????ch h????ng
                //2. l??????y getMax_MaKH v??????a th????m
                //3. c????c c????u l???????nh ph????a d???????????i h????m tr???????????c ????????? l????m
                String TenKH=request.getParameter("form-control");
                String DiaChi=request.getParameter("ct-address");
                String DienThoai=request.getParameter("ct-phone-num");
                KhachHang khachhang=new KhachHang(0, TenKH, DiaChi, DienThoai);
                khachhang_model.insertKhachHang(khachhang);
                System.out.println("khach hang sg");
                int Max_MaKH=khachhang_model.getMaKH_MoiNhat();
                
                //l??????y ng????y hi???????n t??????i (ng????y h??????? th???????ng)
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Calendar cal = Calendar.getInstance();
                String ngayDH = dateFormat.format(cal.getTime());
                //1. insert don hang moi
                DonHangModel donhang_model = new DonHangModel();
                //l????u xu???????ng ?????????n h????ng ngayDH v???? ?????? ?????????y t??????i maKH = 1
                donhang_model.insertDonHang(ngayDH, Max_MaKH); // khach hang c???? m???? l???? 1
                //2. l??????y maDH v??????a insert trong DonHang(t??????c l???? maDH l???????n nh??????t)
                int maDH_MoiNhat = donhang_model.getMaDH_MoiNhat();
                //3. insert chi ti??????t gi??????? h????ng v????o b??????ng chi tiet don h????ng
                //d????ng v????ng l??????p duy???????t qua gi??????? h????ng v???? th????m v????o b??????ng CTDH
                ChiTietDonHangModel ctdh_model = new ChiTietDonHangModel();
                ArrayList<Item> giohang = model.getListItems();// l??????y t??????ng item trong gi??????? h????ng ra
                int kq = 0;
                for (Item i : giohang) {
                    ChiTietDonHang ctdh = new ChiTietDonHang(maDH_MoiNhat, i.getSanpham().getMaSP(), i.getSanpham().getDonGia(), i.getSoluong());
                    kq = ctdh_model.insertChiTietDonHang(ctdh);
                }
                //4. x????a gi??????? h????ng
                model.removeAllSanPham();//sau khi th????m xu???????ng database th???? ta x????a gi??????? h????ng
                page = "thongbao.jsp";// chuy??????n qua trang thongbao.jsp xu??????t ra maDH v??????a th????m
                message = "Order number " + maDH_MoiNhat + " has been paid successfully";
                request.setAttribute("thongbao", message);
                request.getRequestDispatcher(page).forward(request, response);
                return;
            }
            // chuy??????n trang
            page = "giohang.jsp";
            // 2. L??????y gi??????? h????ng ??????????? trang giohang.jsp xu??????t ra c????c item ????????? mua
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
