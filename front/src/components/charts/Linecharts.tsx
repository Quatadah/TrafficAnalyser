import React from "react";
import { useTheme } from "@mui/material/styles";
import {
    LineChart,
    Line,
    XAxis,
    YAxis,
    CartesianGrid,
    Tooltip,
    Legend,
    ResponsiveContainer,
} from "recharts";

import { Typography } from "@mui/material";

export interface traffic {
    values: { avgPL: number; avgVL: number; avg2R: number; hour: number }[];
    direction: string;
}

type Props = {
    width?: number;
    height?: number;
    data?: traffic;
};

const LineCharts: React.FC = ({ width, height, data }: Props) => {
    const theme = useTheme();
    return (
        <>
            <Typography variant="body1" fontWeight={"bold"}>
                Traffic {data?.direction}
            </Typography>
            {data && (
                <ResponsiveContainer width={width} height={height}>
                    <LineChart data={data.values} barSize={40}>
                        <XAxis dataKey="hour" />
                        <YAxis />
                        <CartesianGrid stroke="#ccc" strokeDasharray="5 5" />
                        <Tooltip />
                        <Legend />
                        <Line
                            type="monotone"
                            dataKey="avgPL"
                            stroke={theme.palette.warning.main}
                        />
                        <Line
                            type="monotone"
                            dataKey="avgVL"
                            stroke={theme.palette.primary.main}
                            width={4}
                        />
                        <Line
                            type="monotone"
                            dataKey="avg2R"
                            stroke={theme.palette.success.main}
                        />
                    </LineChart>
                </ResponsiveContainer>
            )}
        </>
    );
};

export default LineCharts;
