import React, { useEffect, useState } from "react";
import {
  Container,
  Row,
  Col,
  Card,
  Button,
  Badge,
  Spinner,
} from "react-bootstrap";
import bookingService from "../../services/bookingService";
import BookingFormModal from "../components/models/BookingFormModal";

const PAGE_SIZE = 6;

function AdminBookings() {
  const [bookings, setBookings] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  const [showForm, setShowForm] = useState(false);
  const [editingBooking, setEditingBooking] = useState(null);
  const [currentPage, setCurrentPage] = useState(1);

  /* ================= LOAD DATA ================= */
  const loadBookings = async () => {
    try {
      setLoading(true);
      const res = await bookingService.getAllBookings();
      setBookings(Array.isArray(res.data) ? res.data : []);
      setError("");
    } catch (err) {
      console.error(err);
      setError("Không thể tải danh sách booking");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadBookings();
  }, []);

  /* ================= HANDLERS ================= */
  const handleAdd = () => {
    setEditingBooking(null);
    setShowForm(true);
  };

  const handleEdit = (booking) => {
    setEditingBooking(booking);
    setShowForm(true);
  };

  const handleDelete = async (id) => {
    if (!window.confirm("Bạn chắc chắn muốn xóa booking này?")) return;
    try {
      await bookingService.deleteBooking(id);
      loadBookings();
    } catch (err) {
      console.error(err);
      alert("Xóa booking thất bại");
    }
  };

  const handleSave = async (formData) => {
    try {
      if (editingBooking) {
        await bookingService.update(editingBooking.id, formData);
      } else {
        await bookingService.create(formData);
      }
      setShowForm(false);
      loadBookings();
    } catch (err) {
      console.error(err);
      alert("Lưu booking thất bại");
    }
  };

  /* ================= PAGINATION ================= */
  const totalPages = Math.ceil(bookings.length / PAGE_SIZE);
  const paginatedBookings = bookings.slice(
    (currentPage - 1) * PAGE_SIZE,
    currentPage * PAGE_SIZE
  );

  /* ================= RENDER STATE ================= */
  if (loading) {
    return (
      <div className="text-center py-5">
        <Spinner animation="border" />
        <p className="mt-2 text-muted">Đang tải booking...</p>
      </div>
    );
  }

  if (error) {
    return <div className="alert alert-danger text-center">{error}</div>;
  }

  if (!bookings.length) {
    return <div className="alert alert-info text-center">Chưa có booking nào</div>;
  }

  /* ================= RENDER UI ================= */
  return (
    <Container>
      <div className="d-flex justify-content-between align-items-center mb-3">
        <h2>Quản lý booking</h2>
        <Button variant="success" onClick={handleAdd}>
          + Thêm booking
        </Button>
      </div>

      <Row className="g-4">
        {paginatedBookings.map((b) => (
          <Col lg={4} md={6} key={b.id}>
            <Card className="h-100 shadow-sm">
              <Card.Body>
                <Card.Title className="fw-bold text-primary">
                  Booking 
                </Card.Title>

                <Card.Text className="text-muted">
                  User: {b.userFullname || b.userId} <br />
                  Hotel: {b.hotelName || b.hotelId} <br />
                  Room Number: {b.roomNumber || b.roomId} <br />
                  Check-in: {new Date(b.checkingDate).toLocaleDateString()} <br />
                  Check-out: {new Date(b.checkoutDate).toLocaleDateString()} <br />
                  Tổng tiền: ${b.totalAmount}
                </Card.Text>

                <Badge
                  bg={
                    b.status === "PENDING"
                      ? "warning"
                      : b.status === "CONFIRMED"
                        ? "success"
                        : "danger"
                  }
                  className="mb-3"
                >
                  {b.status}
                </Badge>

                <div className="d-flex justify-content-end gap-2">
                  <Button
                    size="sm"
                    variant="warning"
                    onClick={() => handleEdit(b)}
                  >
                    Edit
                  </Button>
                  <Button
                    size="sm"
                    variant="danger"
                    onClick={() => handleDelete(b.id)}
                  >
                    Delete
                  </Button>
                </div>
              </Card.Body>
            </Card>
          </Col>
        ))}
      </Row>

      {/* Pagination */}
      {totalPages > 1 && (
        <div className="d-flex justify-content-center mt-4 gap-2">
          <Button
            variant="outline-primary"
            disabled={currentPage === 1}
            onClick={() => setCurrentPage((p) => p - 1)}
          >
            Prev
          </Button>
          <span className="align-self-center">
            {currentPage} / {totalPages}
          </span>
          <Button
            variant="outline-primary"
            disabled={currentPage === totalPages}
            onClick={() => setCurrentPage((p) => p + 1)}
          >
            Next
          </Button>
        </div>
      )}

      {/* Form modal */}
      <BookingFormModal
        show={showForm}
        handleClose={() => setShowForm(false)}
        booking={editingBooking}
        onSave={handleSave}
      />
    </Container>
  );
}

export default AdminBookings;
