import { Drawer, IconButton, Typography } from "@mui/material";
import Button from "@mui/material/Button";
import { Box } from "@mui/system";
import ArrowForwardIosIcon from "@mui/icons-material/ArrowForwardIos";
import ArrowBackIosNew from "@mui/icons-material/ArrowBackIosNew";
import DashboardIcon from "@mui/icons-material/Dashboard";
import SettingsIcon from "@mui/icons-material/Settings";
import React from "react";
import { useTheme } from "@mui/material/styles";

type Props = {};

const SideBar = (props: Props) => {
    const theme = useTheme();
    const [open, setOpen] = React.useState(true);
    const toggleDrawer =
        (open: boolean) => (event: React.KeyboardEvent | React.MouseEvent) => {
            setOpen(open);
        };

    return (
        <Box
            sx={{
                marginTop: 2,
            }}
        >
            <Box
                sx={{
                    display: "flex",
                    flexDirection: "column",
                    alignItems: "center",
                    justifyContent: "space-between",
                    height: "100%",
                    backgroundColor: theme.palette.background.default,
                }}
            >
                <IconButton onClick={toggleDrawer(true)}>
                    <ArrowForwardIosIcon color="primary" />
                </IconButton>
            </Box>
            <Drawer
                variant="persistent"
                sx={{
                    width: 60,
                    flexShrink: 0,
                    "& .MuiDrawer-paper": {
                        width: 60,
                        boxSizing: "border-box",
                    },
                }}
                anchor="left"
                open={open}
            >
                <Box
                    sx={{
                        paddingTop: 2,
                        display: "flex",
                        flexDirection: "column",
                        alignItems: "center",
                        justifyContent: "space-between",
                        height: "100%",
                        backgroundColor: theme.palette.background.paper,
                    }}
                >
                    <IconButton onClick={toggleDrawer(false)}>
                        <ArrowBackIosNew color="primary" />
                    </IconButton>
                    <Box
                        sx={{
                            display: "flex",
                            flexDirection: "column",
                            alignItems: "center",
                            justifyContent: "space-between",
                        }}
                    >
                        <IconButton size="large">
                            <DashboardIcon color="primary" />
                        </IconButton>
                        <IconButton size="large">
                            <SettingsIcon color="disabled" />
                        </IconButton>
                    </Box>
                    <Box></Box>
                </Box>
            </Drawer>
        </Box>
    );
};

export default SideBar;
