import React from "react";
import { Link, useLocation } from "react-router-dom";

function AdminSidebar() {
  const location = useLocation();
  const menuItems = [
    { path: "/admin", label: "Tổng quan", icon: "📊" },
    { path: "/admin/users", label: "Quản lý người dùng", icon: "👤" },
    { path: "/admin/rooms", label: "Quản lý phòng", icon: "🛏️" },
    { path: "/admin/hotels", label: "Quản lý khách sạn", icon: "🏨" },
    { path: "/admin/bookings", label: "Quản lý đặt phòng", icon: "📅" },
  ];

  const isActive = (path) => location.pathname.startsWith(path);

  return (
    <div
      className="d-flex flex-column vh-100 p-3"
      style={{
        width: "240px",
        background: "linear-gradient(180deg, #1e3a8a 0%, #3b82f6 100%)",
        color: "#fff",
        boxShadow: "2px 0 12px rgba(0,0,0,0.15)",
        borderRadius: "0 12px 12px 0",
      }}
    >
      <div className="mb-4 text-center fw-bold fs-5" style={{ color: "#fff" }}>
        Quản trị hệ thống
      </div>

      <ul className="nav nav-pills flex-column mb-auto">
        {menuItems.map((item) => (
          <li className="nav-item mb-2" key={item.path}>
            <Link
              to={item.path}
              className={`nav-link d-flex align-items-center`}
              style={{
                borderRadius: "12px",
                padding: "12px 16px",
                color: isActive(item.path) ? "#1e3a8a" : "#fff",
                backgroundColor: isActive(item.path) ? "#fff" : "transparent",
                fontWeight: isActive(item.path) ? "600" : "500",
                transition: "all 0.3s ease",
                cursor: "pointer",
              }}
              onMouseEnter={(e) => {
                if (!isActive(item.path)) {
                  e.target.style.backgroundColor = "rgba(255,255,255,0.2)";
                }
              }}
              onMouseLeave={(e) => {
                if (!isActive(item.path)) {
                  e.target.style.backgroundColor = "transparent";
                }
              }}
            >
              <span className="me-2">{item.icon}</span>
              {item.label}
            </Link>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default AdminSidebar;
