describe('Kiểm thử tích hợp FE-BE: Chức năng Đăng nhập', () => {
  
  beforeEach(() => {
    cy.visit('http://localhost:5173/login'); 
  });

  it('TC01: Đăng nhập thành công với tài khoản thật trong DB', () => {
    cy.get('input[name="email"]').type('nghi123@gmail.com'); 
    cy.get('input[name="password"]').type('nghi123'); 
    
    // Nhấn nút Login
    cy.get('button[type="submit"]').click();

    // KIỂM TRA KẾT QUẢ:
    // 1. URL phải chuyển hướng về trang chủ hoặc dashboard
    cy.url().should('eq', 'http://localhost:5173/'); 
    
    // 2. Kiểm tra xem Token có được lưu vào LocalStorage không (Rất quan trọng cho đồ án)
    cy.window().then((win) => {
      expect(win.localStorage.getItem('token')).to.exist;
    });

    // 3. Hiển thị thông báo chào mừng hoặc tên user
    cy.contains('Welcome').should('be.visible');
  });

  it('TC02: Đăng nhập thất bại - Sai mật khẩu', () => {
    cy.get('input[name="email"]').type('admin@gmail.com');
    cy.get('input[name="password"]').type('sai_mat_khau_123');
    cy.get('button[type="submit"]').click();

    // Kiểm tra thông báo lỗi trả về từ Backend (ví dụ: 401 Unauthorized)
    // Bạn thay 'Invalid credentials' bằng câu báo lỗi thực tế của BE bạn
    cy.contains('Invalid credentials').should('be.visible');
  });
});