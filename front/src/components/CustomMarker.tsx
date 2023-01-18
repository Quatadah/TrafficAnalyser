import React from "react";
import { Marker, Popup } from "react-leaflet";
import cameraIcon from "../assets/cameraIcon.png";
import tubeIcon from "../assets/tubeIcon.png";
import radarIcon from "../assets/radarIcon.png";
import { Icon } from "leaflet";
import { Typography } from "@mui/material";

type Props = {
    title: string;
    type: string;
    position: [number, number];
    description?: string;
};

const CustomMarker = ({ title, type, position, description }: Props) => {
    const cameraMarker = new Icon({
        iconUrl: cameraIcon,
        iconRetinaUrl: cameraIcon,
        popupAnchor: [-0, -0],
        iconSize: [30, 30],
    });

    const tubeMarker = new Icon({
        iconUrl: tubeIcon,
        iconRetinaUrl: tubeIcon,
        popupAnchor: [-0, -0],
        iconSize: [30, 30],
    });

    const radarMarker = new Icon({
        iconUrl: radarIcon,
        iconRetinaUrl: radarIcon,
        popupAnchor: [-0, -0],
        iconSize: [30, 30],
    });

    return (
        <Marker
            position={position}
            icon={
                type === "radar"
                    ? radarMarker
                    : type === "camera"
                    ? cameraMarker
                    : tubeMarker
            }
        >
            <Popup>
                <Typography
                    variant="body1"
                    fontWeight="bold"
                    textAlign="center"
                >
                    {title}
                </Typography>
                <Typography variant="body2" textAlign="center">
                    {type}
                </Typography>
                <Typography variant="body2">{description}</Typography>
            </Popup>
        </Marker>
    );
};

export default CustomMarker;
