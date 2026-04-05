import React, { useEffect, useState } from "react";
import { Container, Row, Col, Card } from "react-bootstrap";
import { FaUsers, FaBed, FaHotel, FaBook, FaDollarSign } from "react-icons/fa";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../../contexts/AuthContext";

import {
  BarChart, Bar, XAxis, YAxis, Tooltip, ResponsiveContainer,
  PieChart, Pie, Cell, Legend
} from "recharts";

import adminService from "../../services/adminService";
import roomService from "../../services/roomService";
import hotelService from "../../services/hotelService";
import bookingService from "../../services/bookingService";

function Dashboard() {
  const navigate = useNavigate();
  const { role, isAuthenticated } = useAuth();

  const [stats, setStats] = useState({
    users: 0,
    rooms: 0,
    hotels: 0,
    bookings: 0,
    revenue: 0,
  });

  useEffect(() => {
    if (!isAuthenticated || role !== "admin") {
      return navigate("/admin-login");
    }

    const loadDashboard = async () => {
      try {
        const [users, rooms, hotels, bookings] = await Promise.all([
          adminService.getAll(),
          roomService.getAll(),
          hotelService.getAll(),
          bookingService.getAllBookings(),
        ]);

        setStats({
          users: users?.length || 0,
          rooms: rooms?.length || 0,
          hotels: hotels?.length || 0,
          bookings: bookings?.length || 0,
          revenue:
            bookings?.reduce((sum, b) => sum + (b.totalAmount || 0), 0) || 0,
        });
      } catch (error) {
        console.error("Dashboard fetch error:", error);
      }
    };

    loadDashboard();
  }, [isAuthenticated, role, navigate]);

  // ===== DATA CHO CHART =====
  const barData = [
    { name: "Users", value: stats.users },
    { name: "Rooms", value: stats.rooms },
    { name: "Hotels", value: stats.hotels },
    { name: "Bookings", value: stats.bookings },
  ];

  const pieData = [
    { name: "Users", value: stats.users },
    { name: "Rooms", value: stats.rooms },
    { name: "Hotels", value: stats.hotels },
    { name: "Bookings", value: stats.bookings },
  ];

  const COLORS = ["#007bff", "#28a745", "#ffc107", "#17a2b8"];

  const statCards = [
    { title: "Users", value: stats.users, icon: <FaUsers size={30} />, bg: "primary" },
    { title: "Rooms", value: stats.rooms, icon: <FaBed size={30} />, bg: "success" },
    { title: "Hotels", value: stats.hotels, icon: <FaHotel size={30} />, bg: "warning" },
    { title: "Bookings", value: stats.bookings, icon: <FaBook size={30} />, bg: "info" },
    {
      title: "Revenue",
      value: `$${stats.revenue.toLocaleString()}`,
      icon: <FaDollarSign size={30} />,
      bg: "danger",
    },
  ];

  return (
    <Container className="mt-4">
      <h2 className="mb-4 text-center">Admin Dashboard</h2>

      {/* ===== CARDS ===== */}
      <Row className="g-4 mb-4">
        {statCards.map((item, index) => (
          <Col key={index} xs={12} sm={6} md={4} lg={3}>
            <Card className={`h-100 text-white bg-${item.bg} shadow`}>
              <Card.Body className="d-flex align-items-center">
                <div className="me-3">{item.icon}</div>
                <div>
                  <Card.Title>{item.value}</Card.Title>
                  <Card.Text>{item.title}</Card.Text>
                </div>
              </Card.Body>
            </Card>
          </Col>
        ))}
      </Row>

      {/* ===== CHARTS ===== */}
      <Row className="g-4">
        {/* Bar Chart */}
        <Col md={6}>
          <Card className="shadow">
            <Card.Body>
              <h5>System Overview</h5>
              <ResponsiveContainer width="100%" height={300}>
                <BarChart data={barData}>
                  <XAxis dataKey="name" />
                  <YAxis />
                  <Tooltip />
                  <Bar dataKey="value" />
                </BarChart>
              </ResponsiveContainer>
            </Card.Body>
          </Card>
        </Col>

        {/* Pie Chart */}
        <Col md={6}>
          <Card className="shadow">
            <Card.Body>
              <h5>Distribution</h5>
              <ResponsiveContainer width="100%" height={300}>
                <PieChart>
                  <Pie
                    data={pieData}
                    dataKey="value"
                    outerRadius={100}
                    label
                  >
                    {pieData.map((entry, index) => (
                      <Cell key={index} fill={COLORS[index % COLORS.length]} />
                    ))}
                  </Pie>
                  <Legend />
                </PieChart>
              </ResponsiveContainer>
            </Card.Body>
          </Card>
        </Col>
      </Row>
    </Container>
  );
}

export default Dashboard;