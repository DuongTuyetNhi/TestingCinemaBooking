package enums;

public enum Hyperlink {
    DangNhap("Đăng nhập"),
    DangKy("Đăng ký"),
    DangXuat("Đăng xuất"),
    ThemPhim("Thêm phim");


    private final String hyperlink;

    Hyperlink(String hyperlink) {
        this.hyperlink = hyperlink;
    }
    public String getValueHyperlink(){
        return hyperlink;
    }
}
