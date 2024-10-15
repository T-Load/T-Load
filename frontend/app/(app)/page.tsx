import { Button as NextUIButton } from "@nextui-org/react";

export default function CustomButton() {
  return (
    <div className="flex justify-center items-center h-screen">
      {/* NextUI Button with Tailwind CSS */}
      <NextUIButton className="bg-blue-400 text-white text-sm md:text-lg p-4 rounded-md hover:bg-purple-400">
        Tailwind + NextUI Button
      </NextUIButton>
    </div>
  );
}
