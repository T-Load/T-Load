import { nextui } from "@nextui-org/react";
import aspectRatio from "@tailwindcss/aspect-ratio";

// eslint-disable-next-line import/no-anonymous-default-export
export default {
  content: [
    "./app/**/*.{js,ts,jsx,tsx}",
    "./components/**/*.{js,ts,jsx,tsx}",
    "./node_modules/flowbite/**/*.js",
    "./node_modules/@nextui-org/theme/dist/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      fontFamily: {
        pretendard: ["var(--font-pretendard)"],
      },
      colors: {
        black: "#1B1B1B",
        "gray-80": "#4B4B4B",
        "gray-70": "#79747E",
        "gray-60": "#A4A1AA",
        "gray-50": "#ADAEB8",
        "gray-40": "#CBC9CF",
        "gray-30": "#DDDDDD",
        "gray-20": "#EEEEEE",
        "gray-10": "#FAFAFA",
        "green-20": "#0B3B2D",
        "green-10": "#F1EFFD",
        "red-20": "#FF472E",
        "red-10": "#FFE4E0",
        "orange-20": "#FF7C1D",
        "orange-10": "#FFF4E8",
        yellow: "#FFC23D",
        "blue-30": "#0085FF",
        "blue-20": "#2EB4FF",
        "blue-10": "#E5F3FF",
      },
      height: {
        "75vh": "75vh",
      },
      scale: {
        97: "0.97",
      },
      boxShadow: {
        hover: "0 4px 8px rgba(0, 0, 0, 0.2)",
      },
      spacing: {
        6: "1.5rem",
      },
    },
  },
  plugins: [
    aspectRatio,
    nextui({
      layout: {
        dividerWeight: "1px",
        disabledOpacity: 0.5,
        fontSize: {
          tiny: "0.75rem",
          small: "0.875rem",
          medium: "1rem",
          large: "1.125rem",
        },
        lineHeight: {
          tiny: "1rem",
          small: "1.25rem",
          medium: "1.5rem",
          large: "1.75rem",
        },
        radius: {
          small: "8px",
          medium: "10px",
          large: "14px",
        },
        borderWidth: {
          small: "1px",
          medium: "2px",
          large: "3px",
        },
      },
      themes: {
        light: {
          layout: {
            hoverOpacity: 0.8,
            boxShadow: {
              small:
                "0px 0px 5px 0px rgb(0 0 0 / 0.02), 0px 2px 10px 0px rgb(0 0 0 / 0.06), 0px 0px 1px 0px rgb(0 0 0 / 0.3)",
              medium:
                "0px 0px 15px 0px rgb(0 0 0 / 0.03), 0px 2px 30px 0px rgb(0 0 0 / 0.08), 0px 0px 1px 0px rgb(0 0 0 / 0.3)",
              large:
                "0px 0px 30px 0px rgb(0 0 0 / 0.04), 0px 30px 60px 0px rgb(0 0 0 / 0.12), 0px 0px 1px 0px rgb(0 0 0 / 0.3)",
            },
          },
        },
        dark: {
          layout: {
            hoverOpacity: 0.9,
            boxShadow: {
              small:
                "0px 0px 5px 0px rgb(0 0 0 / 0.05), 0px 2px 10px 0px rgb(0 0 0 / 0.2), inset 0px 0px 1px 0px rgb(255 255 255 / 0.15)",
              medium:
                "0px 0px 15px 0px rgb(0 0 0 / 0.06), 0px 2px 30px 0px rgb(0 0 0 / 0.22), inset 0px 0px 1px 0px rgb(255 255 255 / 0.15)",
              large:
                "0px 0px 30px 0px rgb(0 0 0 / 0.07), 0px 30px 60px 0px rgb(0 0 0 / 0.26), inset 0px 0px 1px 0px rgb(255 255 255 / 0.15)",
            },
          },
        },
      },
    }),
  ],
};
