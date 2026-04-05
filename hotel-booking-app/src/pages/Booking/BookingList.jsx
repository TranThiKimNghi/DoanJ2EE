import React, { useEffect, useState } from "react";
import { Card, Badge, Button, Spinner, Alert, Row, Col } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import { FaCalendarAlt } from "react-icons/fa"; // Icon lịch
import bookingService from "../../services/bookingService";
import formatCurrency from "../../utils/formatCurrency";

function BookingList() {
  const [bookings, setBookings] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const loadBookings = async () => {
    try {
      const res = await bookingService.getMyBookings();
      setBookings(res.data);
    } catch (err) {
      setError("Không thể tải danh sách booking");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadBookings();
  }, []);

  const handleCancel = async (id) => {
    if (!window.confirm("Bạn chắc chắn muốn huỷ booking này?")) return;

    try {
      await bookingService.delete(id);
      setBookings(bookings.filter(b => b.id !== id));
    } catch {
      alert("Huỷ booking thất bại");
    }
  };

  const handleSuggestSchedule = (booking) => {
    // Navigate tới /schedule và truyền booking
    navigate("/schedule", { state: { booking } });
  };

  const renderStatus = (status) => {
    switch (status) {
      case "pending": return <Badge bg="warning">Pending</Badge>;
      case "confirmed": return <Badge bg="success">Confirmed</Badge>;
      case "cancelled": return <Badge bg="secondary">Cancelled</Badge>;
      default: return <Badge bg="dark">{status}</Badge>;
    }
  };

  if (loading) {
    return (
      <div className="text-center py-5">
        <Spinner animation="border" />
      </div>
    );
  }

  return (
    <div className="container py-4">
      <h3 className="mb-4 text-primary">📋 Booking của tôi</h3>

      {error && <Alert variant="danger">{error}</Alert>}
      {bookings.length === 0 && <Alert variant="info">Bạn chưa có booking nào</Alert>}

      <Row className="g-3">
        {bookings.map((b) => (
          <Col key={b.id} md={6} lg={4}>
            <Card className="h-100 shadow-sm rounded-4 border-0 hover-card">
              <Card.Body className="d-flex flex-column justify-content-between">
                <div>
                  <div className="d-flex justify-content-between align-items-center mb-2">
                    <h5>Booking </h5>
                    <div>{renderStatus(b.status)}</div>
                  </div>
                  <p className="text-muted small mb-1">
                    {b.checkingDate} → {b.checkoutDate}
                  </p>
                  <div className="fw-bold mb-2">{formatCurrency(b.totalAmount)}</div>
                </div>

                <div className="d-flex gap-2 flex-wrap">
                  <Button
                    size="sm"
                    variant="primary"
                    onClick={() =>
                      navigate("/booking-detail", { state: { booking: b } })
                    }
                  >
                    Chi tiết
                  </Button>

                  {b.status === "pending" && (
                    <Button
                      size="sm"
                      variant="outline-danger"
                      onClick={() => handleCancel(b.id)}
                    >
                      Huỷ
                    </Button>
                  )}

                  <Button
                    size="sm"
                    variant="outline-success"
                    onClick={() => handleSuggestSchedule(b)}
                  >
                    <FaCalendarAlt className="me-1" /> Gợi ý lịch trình
                  </Button>
                </div>
              </Card.Body>
            </Card>
          </Col>
        ))}
      </Row>
    </div>
  );
}

export default BookingList;
