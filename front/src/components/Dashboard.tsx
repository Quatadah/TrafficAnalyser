import {
    ButtonGroup,
    FormControlLabel,
    FormGroup,
    IconButton,
    Switch,
    Typography,
    styled,
    useTheme,
} from "@mui/material";
import { Box } from "@mui/system";
import React, { LegacyRef, useEffect, useRef } from "react";
import Card from "./Card";
import Map from "./Map";
import * as d3 from "d3";
import LineChartExample from "./charts/Linecharts";
import BarChartExample from "./charts/Barcharts";
import PieChartExample from "./charts/Piecharts";
import LightModeIcon from "@mui/icons-material/LightMode";
import DarkModeIcon from "@mui/icons-material/DarkMode";

type Props = {
    onThemeChange: (theme: string) => void;
};

const Dashboard = ({ onThemeChange }: Props) => {
    const [date, setDate] = React.useState(new Date().toLocaleDateString());
    const [time, setTime] = React.useState(new Date().toLocaleTimeString());

    const theme = useTheme();

    useEffect(() => {
        const interval = setInterval(() => {
            setDate(new Date().toLocaleDateString());
            setTime(new Date().toLocaleTimeString());
        }, 1000);
        return () => clearInterval(interval);
    }, []);

    return (
        <Box className="flex flex-col space-y-2 h-full w-full">
            <Box
                className="flex justify-between items-center rounded-md p-3 w-full"
                sx={{
                    backgroundColor: "background.paper",
                }}
            >
                <Typography variant="h6" color="primary" fontWeight="bold">
                    Traffic Analyser
                </Typography>
                <ButtonGroup
                    className="flex justify-center items-center space-x-2"
                    variant="text"
                    color="primary"
                >
                    <Typography
                        variant="body1"
                        color="primary"
                        fontWeight={"bold"}
                    >
                        {date}
                    </Typography>
                    <Typography
                        variant="body1"
                        color="primary"
                        fontWeight={"bold"}
                    >
                        {time}
                    </Typography>
                    <IconButton
                        onClick={() => {
                            onThemeChange(theme.palette.mode);
                            console.log(theme.palette.mode);
                        }}
                    >
                        {theme.palette.mode === "dark" ? (
                            <LightModeIcon />
                        ) : (
                            <DarkModeIcon />
                        )}
                    </IconButton>
                </ButtonGroup>
            </Box>
            <Box className="flex space-x-3 w-full md:h-4/5 h-1/5">
                <Box className="w-1/4 h-full">
                    <Card chart={LineChartExample} />
                </Box>
                <Box className="w-1/4">
                    <Card chart={BarChartExample} />
                </Box>
                <Box className="w-1/4">
                    <Card chart={PieChartExample} />
                </Box>
                <Box className="w-1/4">
                    <Card></Card>
                </Box>
            </Box>
            <Box id="aze" className="flex space-x-3 w-full h-full">
                <Box className="w-3/4">
                    <Card fullScreen={false}>
                        <Map />
                    </Card>
                </Box>
                <Box className="w-1/4">
                    <Card></Card>
                </Box>
            </Box>{" "}
        </Box>
    );
};

export default Dashboard;
