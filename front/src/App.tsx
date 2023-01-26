import React, { useEffect, useState, useMemo } from "react";
import "./App.css";
import { ThemeProvider, createTheme } from "@mui/material/styles";
import CssBaseline from "@mui/material/CssBaseline";
import SideBar from "./components/SideBar";
import { Box } from "@mui/system";
import Dashboard from "./components/Dashboard";

const App = () => {
    const [mode, setMode] = useState<"light" | "dark">("dark");

    const handleThemeChange = () => {
        setMode(mode === "dark" ? "light" : "dark");
    };

    const theme = useMemo(
        () =>
            createTheme({
                palette: {
                    mode,
                    primary: {
                        main: "#31978C",
                        light: "#6FCF97",
                    },
                    secondary: {
                        main: "#435055",
                    },
                    background: {
                        default: mode === "dark" ? "#1A1A1C" : "#fff",
                        paper: mode === "dark" ? "#121212" : "#eee",
                    },
                },
                typography: {
                    fontFamily: "Poppins",
                },
            }),
        [mode]
    );

    useEffect(() => {
        (async () => {
            const WebFont = await import("webfontloader");
            WebFont.load({
                google: {
                    families: ["Poppins:300,700", "sans-serif"],
                },
            });
        })();
    }, []);

    return (
        <ThemeProvider theme={theme}>
            <CssBaseline />
            <Box>
                <Box
                    sx={{
                        display: "flex",
                        flexDirection: "row",
                    }}
                >
                    <SideBar />
                    <Box
                        className="h-screen"
                        sx={{
                            flexGrow: 1,
                            padding: 1,
                        }}
                    >
                        <Dashboard onThemeChange={handleThemeChange} />
                    </Box>
                </Box>
            </Box>
        </ThemeProvider>
    );
};

export default App;
