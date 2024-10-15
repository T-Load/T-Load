// app/layout.tsx
import { ReactNode } from "react";

import "../styles/global.css";

export default function RootLayout({ children }: { children: ReactNode }) {
  return (
    <html lang="ko">
      <head>
        <title>T-load</title>
        <meta name="description" content="This is an awesome website about trip." />
        <meta name="keywords" content="trip, tour, tourist, communication" />
        <meta property="og:title" content="T-Load" />
        <meta property="og:description" content="This is an awesome website about trip." />
        <meta property="og:image" content="" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />
      </head>
      <body>{children}</body>
    </html>
  );
}
