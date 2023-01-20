import { Typography } from "@mui/material";
import { Box } from "@mui/system";
import React from "react";
import Card from "./Card";
import Map from "./Map";

type Props = {};

const Dashboard = (props: Props) => {
    return (
        <Box className="flex flex-col space-y-12 h-full w-full">
            <Box
                className="rounded-md p-3 w-full"
                sx={{
                    backgroundColor: "background.paper",
                }}
            >
                <Typography variant="h6" color="primary" fontWeight="bold">
                    Traffic Analyser
                </Typography>
            </Box>
            <Box className="flex space-x-6 w-full md:h-2/5 h-1/5">
                <Box className="w-1/4 h-full">
                    <Card>
                        <Typography
                            variant="h6"
                            textAlign={"center"}
                            fontWeight="bold"
                        >
                            Total Traffic
                        </Typography>
                        <Typography variant="h4" textAlign={"center"}>
                            100
                        </Typography>
                    </Card>
                </Box>
                <Box className="w-1/4">
                    <Card>
                        <Typography variant="h6" textAlign={"center"}>
                            Total Traffic
                        </Typography>
                        <Typography variant="h4" textAlign={"center"}>
                            100
                        </Typography>
                    </Card>
                </Box>
                <Box className="w-1/4">
                    <Card>
                        <Typography variant="h6" textAlign={"center"}>
                            Total Traffic
                        </Typography>
                        <Typography variant="h4" textAlign={"center"}>
                            100
                        </Typography>
                    </Card>
                </Box>
                <Box className="w-1/4">
                    <Card>
                        <Typography variant="h6" textAlign={"center"}>
                            Total Traffic
                        </Typography>
                        <Typography variant="h4" textAlign={"center"}>
                            100
                        </Typography>
                    </Card>
                </Box>
            </Box>
            <Box id="aze" className="flex space-x-6 w-full h-full">
                <Box className="w-3/4">
                    <Card>
                        <Map />
                    </Card>
                </Box>
                <Box className="w-1/4">
                    <Card>
                        <Typography
                            variant="h6"
                            color="primary"
                            textAlign={"center"}
                        >
                            Total Traffic
                        </Typography>
                        <Typography
                            variant="h4"
                            color="primary"
                            textAlign={"center"}
                        >
                            100
                        </Typography>
                    </Card>
                </Box>
            </Box>{" "}
        </Box>
    );
};

export default Dashboard;
