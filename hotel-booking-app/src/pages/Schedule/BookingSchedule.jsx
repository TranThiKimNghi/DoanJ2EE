import React, { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { Accordion, Card, Badge, Spinner, Button } from "react-bootstrap";
import { getBookingItinerary } from "../../services/scheduleService";

const BookingSchedule = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const booking = location.state?.booking;

  const [days, setDays] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchItinerary = async () => {
      if (!booking?.id) {
        setError("Không tìm thấy booking.");
        setLoading(false);
        return;
      }

      try {
        const data = await getBookingItinerary(booking.id);
        setDays(Array.isArray(data.days) ? data.days : []);
      } catch (err) {
        console.error(err);
        setError("Không thể tải lịch trình.");
      } finally {
        setLoading(false);
      }
    };

    fetchItinerary();
  }, [booking]);

  // =========================
  // UI STATES
  // =========================
  if (loading)
    return (
      <Spinner animation="border" className="d-block mx-auto my-5" />
    );

  if (error)
    return <p className="text-center text-danger my-5">{error}</p>;

  if (!days || days.length === 0)
    return (
      <p className="text-center my-5">
        Không có dữ liệu lịch trình.
      </p>
    );

  // =========================
  // RENDER
  // =========================
  return (
    <div className="container py-5">
      <h2 className="mb-4 text-center">
        Lịch trình Booking
      </h2>

      {/* Accordion theo NGÀY */}
      <Accordion defaultActiveKey="0" alwaysOpen>
        {days.map((day, dayIndex) => (
          <Accordion.Item
            eventKey={dayIndex.toString()}
            key={dayIndex}
          >
            {/* HEADER NGÀY */}
            <Accordion.Header>
              <strong>Ngày {dayIndex + 1}</strong>
            </Accordion.Header>

            {/* BODY NGÀY */}
            <Accordion.Body>

              {/* LOOP CÁC BUỔI */}
              {Object.entries(day.sessions).map(
                ([sessionName, places], i) => (
                  <Card className="mb-4 shadow-sm" key={i}>
                    <Card.Header className="bg-light">
                      <h5 className="mb-0 text-primary">
                        {sessionName} ({places.length} địa điểm)
                      </h5>
                    </Card.Header>

                    <Card.Body>
                      {places.length === 0 ? (
                        <p>Không có địa điểm nào.</p>
                      ) : (
                        <div className="table-responsive">
                          <table className="table table-striped table-hover">
                            <thead>
                              <tr>
                                <th>Tên địa điểm</th>
                                <th>Loại</th>
                                <th>Địa chỉ</th>
                                <th>Rating</th>
                                <th>Khoảng cách (m)</th>
                              </tr>
                            </thead>
                            <tbody>
                              {places.map((place) => (
                                <tr key={place.id}>
                                  <td>{place.placeName}</td>

                                  <td>
                                    <Badge bg="info">
                                      {place.category}
                                    </Badge>
                                  </td>

                                  <td>{place.address}</td>

                                  <td>
                                    <Badge bg="warning">
                                      {place.rating}
                                    </Badge>
                                  </td>

                                  <td>{place.distance}</td>
                                </tr>
                              ))}
                            </tbody>
                          </table>
                        </div>
                      )}
                    </Card.Body>
                  </Card>
                )
              )}
            </Accordion.Body>
          </Accordion.Item>
        ))}
      </Accordion>

      {/* BACK BUTTON */}
      <div className="text-center mt-4">
        <Button
          variant="outline-primary"
          onClick={() => navigate("/bookings/me")}
        >
          ← Quay lại
        </Button>
      </div>
    </div>
  );
};

export default BookingSchedule;