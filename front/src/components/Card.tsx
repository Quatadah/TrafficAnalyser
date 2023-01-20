import Box from "@mui/material/Box";
import { width } from "@mui/system";
import React from "react";

type Props = {
    children?: React.ReactNode;
};

const Card = ({ children }: Props) => {
    return (
        <Box
            className="h-full rounded-md p-2"
            sx={{
                backgroundColor: "background.paper",
            }}
        >
            {children}
        </Box>
    );
};

export default Card;
