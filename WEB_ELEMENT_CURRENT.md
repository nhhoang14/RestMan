# Danh sach phan tu giao dien hien tai

Tai lieu mo ta cac phan tu co giao dien thuc te trong thu muc `src/main/webapp/customer` va `src/main/webapp/manager`.

## Thu muc customer

### CustomerMainView.jsp

#### Thanh dieu huong

- `brandLink : link`
  - Hien thi chu `RestMan`.
  - Chuyen den `/customer/CustomerMainView.jsp`.
- `customerGreeting : label`
  - Hien thi ten khach hang trong session.
  - Neu chua dang nhap: `Xin chao, khach hang`.
- `customerLogoutButton : link`
  - Xuat hien khi `sessionScope.customer` ton tai.
  - Goi `/login?action=logout`.
- `customerLoginButton : link`
  - Xuat hien khi khach chua dang nhap.
  - Chuyen den `/login`.

#### Noi dung chinh

- `pageTitle : label`
  - Noi dung: `Trang chinh khach hang`.
- `btnSearchMenu : link`
  - Chuyen den `/menuitem?keyword=`.
  - Hanh dong mo trang tim kiem va co the tim khong can dang nhap.

### SearchMenuItemView.jsp

#### Thanh dieu huong

- `brandLink : link`
  - Chuyen den `/customer/CustomerMainView.jsp`.
- `customerGreeting : label`
  - Hien thi ten khach hang dang nhap trong session.
- `customerLogoutButton : link`
  - Chi hien khi co `sessionScope.customer`.
- `customerLoginButton : link`
  - Chi hien khi khach chua dang nhap.

#### Tim kiem va du lieu

- `btnBackCustomerMain : link`
  - Quay lai `/customer/CustomerMainView.jsp`.
- `pageTitle : label`
  - Noi dung: `Tim kiem mon an`.
- `inKeyword : search input`
  - Thuoc tinh `name=keyword`.
  - Gia tri mac dinh la request attribute `keyword`.
- `btnSearchMenu : submit button`
  - Gui form GET den `/menuitem`.
- `tblMenuItems : table`
  - Nguon du lieu: request attribute `menuItems`.
- `menuItems : MenuItem6[]`
  - Danh sach ket qua tim kiem.
- `menuItem.id : int`
- `menuItem.name : String`
- `menuItem.price : float`
- `btnViewMenuItemDetail : link`
  - Goi `/menuitem?action=detail&id=<menuItem.id>`.

### MenuItemDetailView.jsp

#### Thanh dieu huong

- `brandLink : link`
  - Chuyen den `/customer/CustomerMainView.jsp`.
- `customerGreeting : label`
  - Hien thi ten khach hang dang nhap trong session.
- `customerLogoutButton : link`
  - Chi hien khi co `sessionScope.customer`.
- `customerLoginButton : link`
  - Chi hien khi khach chua dang nhap.

#### Chi tiet mon an

- `menuItemTitle : label`
  - Hien thi `menuItem.name`.
- `menuItemImage : image`
  - Hien thi tu `menuItem.image`.
  - Hien thi URL bat dau bang `http://` hoac `https://` truc tiep.
  - Hien thi duong dan noi bo bang `${contextPath}/${menuItem.image}`.
- `menuItemImagePlaceholder : label`
  - Noi dung `Chua co hinh anh` khi khong co anh.
- `tblMenuItemDetail : table`
- `menuItem : MenuItem6`
  - Thuoc tinh: `id`, `name`, `image`, `description`, `price`, `category`.
- `menuItemCategory : Category6`
  - Hien thi `menuItem.category.name`.

#### Thuoc tinh rieng cua dish

- `dishSize : String`
  - Duoc dat boi `MenuItemServlet` khi `menuItem instanceof Dish6`.
- `dishUnit : String`
  - Duoc dat boi `MenuItemServlet` khi `menuItem instanceof Dish6`.
- `rowDishSize : table row`
  - Chi hien khi co `dishSize`.
- `rowDishUnit : table row`
  - Chi hien khi co `dishUnit`.

#### Thuoc tinh rieng cua combo

- `comboStartDate : Date`
  - Duoc dat boi `MenuItemServlet` khi `menuItem instanceof Combo6`.
- `comboEndDate : Date`
  - Duoc dat boi `MenuItemServlet` khi `menuItem instanceof Combo6`.
- `rowComboStartDate : table row`
- `rowComboEndDate : table row`
- `tblComboDetails : table`
- `comboDetails : ComboDetail6[]`
  - Chi hien khi danh sach khong rong.
- `comboDetail.dish.id : int`
- `comboDetail.dish.name : String`
- `comboDetail.dishQuantity : int`
- `comboDetail.dish.price : float`
- `btnBackMenuSearch : link`
  - Chuyen den `/menuitem?keyword=`.

## Thu muc manager

### ManagerMainView.jsp

#### Thanh dieu huong

- `brandLink : link`
  - Chuyen den `/manager/ManagerMainView.jsp`.
- `managerGreeting : label`
  - Lay ten tu `sessionScope.manager.fullName`.
  - Gia tri mac dinh: `quan ly`.
- `managerLogoutButton : link`
  - Goi `/login?action=logout`.

#### Noi dung chinh

- `pageTitle : label`
  - Noi dung: `Trang chinh quan ly`.
- `btnViewReports : link`
  - Chuyen den `/manager/SelectReportView.jsp`.

### SelectReportView.jsp

#### Thanh dieu huong

- `brandLink : link`
- `managerGreeting : label`
- `managerLogoutButton : link`

#### Noi dung

- `btnBackManagerMain : link`
  - Quay lai `/manager/ManagerMainView.jsp`.
- `pageTitle : label`
  - Noi dung: `Chon loai thong ke`.
- `btnCustomerRevenueReport : link`
  - Chuyen den `/manager/CustomerRevenueReportView.jsp`.

### CustomerRevenueReportView.jsp

#### Thanh dieu huong

- `brandLink : link`
- `managerGreeting : label`
- `managerLogoutButton : link`

#### Bo loc ngay

- `btnBackSelectReport : link`
  - Quay lai `/manager/SelectReportView.jsp`.
- `pageTitle : label`
  - Noi dung: `Thong ke khach hang theo doanh thu`.
- `formCustomerRevenueReport : form`
  - Method `GET`.
  - Action `/customer-revenue`.
- `inStartDate : date input`
  - Thuoc tinh `name=startDate`.
  - Gia tri `startDate`.
- `inEndDate : date input`
  - Thuoc tinh `name=endDate`.
  - Gia tri `endDate`.
- `btnSearchCustomerRevenue : submit`
  - Goi action report.

#### Bang thong ke

- `tblCustomerStats : table`
- `customerStats : CustomerStats6[]`
  - Noi dung don vi: thong tin khach hang, so dat mon, doanh thu.
- `customer.id : int`
  - Bang 0 danh dau `Khach vang lai`.
- `customer.fullName : String`
- `customer.phone : String`
- `customer.bookingCount : int`
- `customer.totalRevenue : float`
- `linkCustomerHistory : link`
  - Hien cho khach co tai khoan va khach vang lai.
  - Goi `/customer-revenue?action=history&customerId=<id>&startDate=<start>&endDate=<end>`.
- `totalCount : int`
  - Tong so booking cua tat ca khach trong khoang ngay.
- `totalRevenue : float`
  - Tong doanh thu cuoi cung lay tu `Bill6.totalAmount`.
- `rowCustomerStatsTotal : table row`

### CustomerOrderHistoryView.jsp

#### Thanh dieu huong

- `brandLink : link`
- `managerGreeting : label`
- `managerLogoutButton : link`

#### Thong tin lich su

- `btnBackCustomerRevenueReport : link`
  - Giu lai `startDate` va `endDate`.
- `customerTitle : label`
- `customer : Customer6`
  - Khach co tai khoan: lay thong tin khach, co the chi co `id` trong luong hien tai.
  - Khach vang lai: `fullName = Khach vang lai`, `id = 0`.
- `orders : DishOrdering6[]`
- `tblCustomerOrders : table`
- `order.id : int`
- `order.orderDate : Date`
- `order.estimatedTotalAmount : float`
  - Gia tri nay la tong tien sau giam gia lay tu hoa don neu co.
- `order.status : String`

#### Cac nut va tong ket

- `linkOrderDetail : link`
  - Goi `/customer-revenue?action=order&orderId=<id>&customerId=<id>&startDate=<start>&endDate=<end>`.
- `historyTotal : float`
  - Tong tien cuoi cung cua tat ca don trong lich su.
- `rowHistoryTotal : table row`
- `emptyOrdersMessage : label`
  - Hien `Chua co lich su goi mon` khi khong co don.

### OrderDetailView.jsp

#### Thanh dieu huong

- `brandLink : link`
- `managerGreeting : label`
- `managerLogoutButton : link`

#### Chi tiet don

- `btnBackCustomerOrderHistory : link`
  - Giua lai `customerId`, `startDate`, `endDate`.
- `orderTitle : label`
  - Hien `Don dat mon #<order.id>`.
- `order : DishOrdering6`
- `tblOrderDetails : table`
- `order.details : DishOrderingDetail6[]`
- `detail.menuItem.id : int`
- `detail.quantity : int`
- `detail.price : float`
- `detail.amount : float`
  - Tinh bang `quantity * price`.
- `subtotalAmount : float`
  - Hien theo `order.estimatedTotalAmount` sau khi DAO tinh tong chi tiet.
- `rowSubtotalAmount : table row`
  - Tieu de `Tong tam tinh`.
- `bill : Bill6`
- `discountAmount : float`
  - Lay tu `bill.discountAmount`.
- `rowDiscountAmount : table row`
- `finalAmount : float`
  - Lay tu `bill.totalAmount`.
  - Neu khong co hoa don thi dung `order.estimatedTotalAmount`.
- `rowFinalAmount : table row`
- `btnViewBill : link`
  - Goi `/customer-revenue?action=bill&orderId=<id>&customerId=<id>&startDate=<start>&endDate=<end>`.

### BillView.jsp

#### Thanh dieu huong

- `brandLink : link`
- `managerGreeting : label`
- `managerLogoutButton : link`

#### Hoa don

- `btnBackOrderDetail : link`
  - Goi lai action `order` voi `orderId`, `customerId`, `startDate`, `endDate`.
- `billTitle : label`
  - Hien `Hoa don #<bill.id>`.
- `bill : Bill6`
- `tblBillDetail : table`
- `bill.id : int`
- `bill.paymentTime : Date`
- `bill.paymentMethod : String`
- `bill.salesStaff : SalesStaff6`
- `bill.salesStaff.fullName : String`
- `bill.discountAmount : float`
- `bill.totalAmount : float`
  - Tong tien cuoi cung sau khi tru `discountAmount`.

## Trang dang nhap dung chung

Trang nay nam ngoai hai thu muc `customer` va `manager` nhung la dau vao chung cho ca hai vai tro.

### LoginView.jsp

- `brandLink : link`
  - Chuyen den `/`.
- `loginTitle : label`
  - Noi dung `Dang nhap`.
- `errorMessage : label`
  - Hien khi ten dang nhap hoac mat khau khong dung.
- `formLogin : form`
  - Method `POST`.
  - Action `/login`.
- `inUsername : text input`
  - Thuoc tinh `name=username`.
- `inPassword : password input`
  - Thuoc tinh `name=password`.
- `btnSubmitLogin : submit`
  - Xac thuc bang `AuthenticationDAO`.
- `btnBackCustomer : link`
  - Quay ve trang khach hang.
- `manager : Manager6`
  - Hien session khi tai khoan quan ly dang nhap thanh cong.
- `customer : Customer6`
  - Hien session khi tai khoan khach hang dang nhap thanh cong.

