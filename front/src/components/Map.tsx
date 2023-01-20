import { MapContainer, TileLayer } from "react-leaflet";
import "leaflet/dist/leaflet.css";
import "leaflet/dist/images/marker-shadow.png";
import { useState } from "react";
import { Box } from "@mui/system";
import CustomMarker from "./CustomMarker";
import data from "../data/posts";
import Fullscreen from "react-leaflet-fullscreen-plugin";

type Props = {
    children?: React.ReactNode;
};

const Map = (props: Props) => {
    const [mapState, setMapState] = useState({
        lat: 44.80416345,
        lng: -0.599976,
        zoom: 14,
    });
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition((position) => {
            setMapState({
                lat: position.coords.latitude,
                lng: position.coords.longitude,
                zoom: 5,
            });
        });
    }

    const [markers, setMarkers] = useState(data);

    const options = {
        position: "topleft", // change the position of the button can be topleft, topright, bottomright or bottomleft, default topleft
        title: "Show me the fullscreen !", // change the title of the button, default Full Screen
        titleCancel: "Exit fullscreen mode", // change the title of the button when fullscreen is on, default Exit Full Screen
        content: null, // change the content of the button, can be HTML, default null
        forceSeparateButton: true, // force separate button to detach from zoom buttons, default false
        forcePseudoFullscreen: true, // force use of pseudo full screen even if full screen API is available, default false
        fullscreenElement: false, // Dom element to render in full screen, false by default, fallback to map._container
    };

    return (
        <Box className="w-full h-full rounded-md">
            <MapContainer
                id="map"
                center={[mapState.lat, mapState.lng]}
                zoom={mapState.zoom}
                scrollWheelZoom={true}
                style={{ height: "100%", width: "100%", borderRadius: "5px" }}
            >
                <TileLayer
                    className="map-tiles"
                    attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                    url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                />
                {markers.map((marker) => (
                    <CustomMarker
                        key={marker.title}
                        title={marker.title}
                        type={marker.type}
                        position={[
                            marker.location.latitude,
                            marker.location.longitude,
                        ]}
                    />
                ))}
            </MapContainer>
        </Box>
    );
};

export default Map;

/* 
<Fullscreen
                    eventHandlers={{
                        enterFullscreen: (event) =>
                            console.log("entered fullscreen", event),
                        exitFullscreen: (event) =>
                            console.log("exited fullscreen", event),
                    }}
                    position="topleft" // change the position of the button can be topleft, topright, bottomright or bottomleft, default topleft
                    title="Show me the fullscreen !" // change the title of the button, default Full Screen
                    titleCancel="Exit fullscreen mode" // change the title of the button when fullscreen is on, default Exit Full Screen
                    forceSeparateButton={true} // force separate button to detach from zoom buttons, default false
                    forcePseudoFullscreen={true} // force use of pseudo full screen even if full screen API is available, default false
                    fullscreenElement={false} // Dom element to render in full screen, false by default, fallback to map._container
                />
*/
