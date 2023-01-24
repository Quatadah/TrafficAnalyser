import { ButtonGroup, IconButton, Typography, useTheme } from "@mui/material";
import { Box } from "@mui/system";
import React, { useEffect } from "react";
import Card from "./Card";
import Map from "./Map";
import LineChartExample from "./charts/Linecharts";
import BarChartExample from "./charts/Barcharts";
import PieChartExample from "./charts/Piecharts";
import LightModeIcon from "@mui/icons-material/LightMode";
import DarkModeIcon from "@mui/icons-material/DarkMode";
import RadarChart from "./charts/Radarcharts";
import { AdapterLuxon } from "@mui/x-date-pickers/AdapterLuxon";
import TextField from "@mui/material/TextField";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { DatePicker } from "@mui/x-date-pickers/DatePicker";

type Props = {
    onThemeChange: (theme: string) => void;
};

const Dashboard = ({ onThemeChange }: Props) => {
    const [date, setDate] = React.useState(new Date().toLocaleDateString());
    const [time, setTime] = React.useState(new Date().toLocaleTimeString());
    const [value, setValue] = React.useState<Date | null>(new Date());

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
                <Typography variant="h6" color="secondary" fontWeight={"bold"}>
                    {time}
                </Typography>
                <Box className="flex justify-center items-center space-x-2">
                    <LocalizationProvider
                        size="small"
                        dateAdapter={AdapterLuxon}
                    >
                        <DatePicker
                            value={value}
                            onChange={(newValue) => {
                                setValue(newValue);
                            }}
                            renderInput={(params) => <TextField {...params} />}
                        />
                    </LocalizationProvider>
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
                </Box>
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
                    <Card chart={RadarChart}></Card>
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
