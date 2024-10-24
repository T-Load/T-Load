export const buttonSize: {
  [size: string]: string;
} = {
  xs: "h-7 text-xs font-medium rounded-md px-3 py-1.5",
  s: "h-9 text-xs font-medium rounded-md px-3 py-2.5",
  m: "h-10 text-sm font-medium rounded-lg px-3.5 py-2.5",
  l: "h-12 text-sm font-bold rounded-lg px-4 py-3",
  xl: "h-13 text-md font-bold rounded-lg px- py-3",
  "2xl": "h-14 text-lg font-bold rounded-lg px-5 py-4",
  "3xl": "h-18 text-2xl font-bold rounded-xl px-6 py-5",
};

export type buttonSizeType = "xs" | "s" | "m" | "l" | "xl" | "2xl" | "3xl";

export const buttonColor: {
  [color: string]: string;
} = {
  primary: "bg-green-500 text-white hover:bg-green-700",
};

export type buttonColorType = "primary";
