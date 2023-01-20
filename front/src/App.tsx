import React, { useEffect } from "react";
import "./App.css";
import { ThemeProvider, createTheme } from "@mui/material/styles";
import CssBaseline from "@mui/material/CssBaseline";
import SideBar from "./components/SideBar";
import { Box } from "@mui/system";
import Dashboard from "./components/Dashboard";

const darkTheme = createTheme({
    palette: {
        mode: "dark",

        primary: {
            main: "#108F88",
        },
        background: {
            default: "#1A1A1C",
        },
    },
    typography: {
        fontFamily: "Poppins",
    },
    // change drawer background color
    components: {
        MuiDrawer: {
            styleOverrides: {
                paper: {
                    backgroundColor: "#141414",
                },
            },
        },
    },
});

const App = () => {
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
        <ThemeProvider theme={darkTheme}>
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
                            padding: 3,
                        }}
                    >
                        <Dashboard />
                    </Box>
                </Box>
            </Box>
        </ThemeProvider>
    );
};

export default App;
