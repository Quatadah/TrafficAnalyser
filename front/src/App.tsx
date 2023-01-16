import React from "react";
import "./App.css";
import { ThemeProvider, createTheme } from "@mui/material/styles";
import CssBaseline from "@mui/material/CssBaseline";
import SideBar from "./components/SideBar";
import { Box } from "@mui/system";

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

function App() {
    return (
        <ThemeProvider theme={darkTheme}>
            <CssBaseline />
            <Box>
                <Box
                    sx={{
                        display: "flex",
                    }}
                >
                    <SideBar />
                    <Box
                        sx={{
                            flexGrow: 1,
                            bgcolor: "background.default",
                            p: 3,
                        }}
                    >
                        <main>This app is using the dark mode</main>
                    </Box>
                </Box>
            </Box>
        </ThemeProvider>
    );
}

export default App;
