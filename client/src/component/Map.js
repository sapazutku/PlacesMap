import { useState } from "react";

function Map() {
  const [latitude, setLatitude] = useState("37.7937");
  const [longitude, setLongitude] = useState("-122.3965");
  const [radius, setRadius] = useState("500.0");

  const handleSubmit = async (event) => {
    event.preventDefault();
    const response = await fetch(
      `http://localhost:8070/api/places?latitude=${latitude}&longitude=${longitude}&radius=${radius}`
    );

    const data = await response.json();
    console.log(data);
  };
  return (
    <div>
      <form onSubmit={handleSubmit}>
        <div>
          <label>
            Latitude:
            <input
              type="number"
              value={latitude}
              onChange={(e) => setLatitude(e.target.value)}
            />
          </label>
        </div>
        <div>
          <label>
            Longitude:
            <input
              type="number"
              value={longitude}
              onChange={(e) => setLongitude(e.target.value)}
            />
          </label>
        </div>
        <div>
          <label>
            Radius:
            <input
              type="number"
              value={radius}
              onChange={(e) => setRadius(e.target.value)}
            />
          </label>
        </div>
        <button type="submit">Get Places</button>
      </form>
    </div>
  );
}

export default Map;
