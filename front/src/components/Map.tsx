import { MapContainer, TileLayer, TileLayerProps } from "react-leaflet";
import "leaflet/dist/leaflet.css";
import "leaflet/dist/images/marker-shadow.png";
import { useEffect, useRef, useState } from "react";
import { Box } from "@mui/system";
import CustomMarker from "./CustomMarker";
import data from "../data/posts";
import { useTheme } from "@mui/material";

type Props = {
    children?: React.ReactNode;
};

const Map = (props: Props) => {
    const theme = useTheme();
    const [mapState, setMapState] = useState({
        lat: 44.80416345,
        lng: -0.599976,
        zoom: 14,
    });
    const [firstRender, setFirstRender] = useState(true);
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition((position) => {
            setMapState({
                lat: position.coords.latitude,
                lng: position.coords.longitude,
                zoom: 5,
            });
        });
    }

    const tileLayerRef = useRef<any>(null);

    const changeMapMode = () => {
        const tileLayerElement =
            document.getElementsByClassName("leaflet-layer");
        if (theme.palette.mode === "dark") {
            tileLayerElement[0]?.classList?.remove("map-tiles-light");
            tileLayerElement[0]?.classList?.add("map-tiles-dark");
            console.log(tileLayerElement[0].classList);
        } else {
            tileLayerElement[0]?.classList?.remove("map-tiles-dark");
            tileLayerElement[0]?.classList?.add("map-tiles-light");
            console.log(tileLayerElement[0].classList);
        }
    };
    // Change the value of map-tiles class when theme changes
    useEffect(() => {
        if (firstRender) {
            setTimeout(() => {
                changeMapMode();
            }, 1000);
            setFirstRender(false);
        } else {
            changeMapMode();
        }
    }, [theme.palette.mode]);

    const [markers, setMarkers] = useState(data);

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
                    className={
                        theme.palette.mode === "dark"
                            ? "map-tiles-dark"
                            : "map-tiles-light"
                    }
                    ref={tileLayerRef}
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
