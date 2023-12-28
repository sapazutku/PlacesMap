import { useState } from "react";
import "./Map.css";
import { MapContainer, TileLayer, Marker, Popup, Circle } from "react-leaflet";
import L from "leaflet";

function Map() {
  const [latitude, setLatitude] = useState();
  const [longitude, setLongitude] = useState();
  const [radius, setRadius] = useState();
    

  const [places, setPlaces] = useState([]);

  const handleSubmit = async (event) => {
    event.preventDefault();
    const response = await fetch(
      `https://placeapi-utkucodexist.azuremicroservices.io/api/places?latitude=${latitude}&longitude=${longitude}&radius=${radius}`
    );

    const data = await response.json();
    console.log(data);
    setPlaces(data.places);
  };
  return (
    <div className="map-container">
      <form onSubmit={handleSubmit} className="map-form">
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

      <div className="mapview">
        <MapContainer
          center={
            latitude && longitude ? [latitude, longitude] : [37.7937, -122.3965]
          }
          zoom={13}
          style={{ height: "600px", width: "100%" }}
        >
          <TileLayer
            url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
            attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
          />
          {places.map((place, index) => (
            <Marker
              key={index}
              position={[place.location.latitude, place.location.longitude]}
              icon={
                new L.Icon({
                  iconUrl:
                    "https://maps.gstatic.com/mapfiles/ms/icons/red-dot.png",
                  iconSize: [40, 41],
                  iconAnchor: [12, 41],
                  popupAnchor: [1, -34],
                  shadowSize: [41, 41],
                  className: "custom-marker-icon",
                })
              }
            >
              <Popup>{place.displayName.text}</Popup>
            </Marker>
          ))}
          {latitude && longitude && radius && (
            <Circle
              center={[latitude, longitude]}
              radius={radius}
              color="blue"
              fillColor="blue"
              fillOpacity={0.2}
            />
          )}
        </MapContainer>
      </div>
    </div>
  );
}

export default Map;
