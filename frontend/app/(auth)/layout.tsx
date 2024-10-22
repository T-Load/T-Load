"use client";

import { ReactNode } from "react";

export default function AuthLayout({ children }: { children: ReactNode }) {
  return (
    <div className="flex justify-center items-center w-full h-screen bg-gradient-to-b from-green-400 to-pink-300">
      {children}
    </div>
  );
}
